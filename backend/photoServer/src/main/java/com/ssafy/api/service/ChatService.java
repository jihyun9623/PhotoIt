package com.ssafy.api.service;

import com.ssafy.api.response.ChatRes;
import com.ssafy.api.response.ChatRoomRes;
import com.ssafy.api.response.ChatRoomsRes;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.dto.ChatRoomDto;
import com.ssafy.db.entity.TempChatMessage;
import com.ssafy.db.entity.TempChatRoom;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ChatRoomRepository;
import com.ssafy.db.repository.TempChatMessageRepository;
import com.ssafy.db.repository.TempChatRoomRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final TempChatMessageRepository messageRepository;
    private final TempChatRoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoomRes chatList(ChatRoomDto chatRoomDto) {
        List<ChatRes> listChatRes = new ArrayList<>();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("getName : %s\n", chatRoomDto.getName());
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        TempChatRoom tempChatRoom =  roomRepository.findById(chatRoomDto.getName())
                                     .orElseThrow(RuntimeException::new);
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("tempChatRoom.getRoomName : %s\n", chatRoomDto.getName());
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        List<TempChatMessage> a = tempChatRoom.getTempChatMessages();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("tempChatRoom.getRoomName : %s,a.size() : %d\n", tempChatRoom.getRoomName(), a.size());
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for(int i=a.size()-1; i>-1; --i) {
            String temp = userRepository.findUserById(a.get(i).getSenderName()).orElseThrow(RuntimeException::new).getNickname();
            ChatRes chatRes = ChatRes.of(temp, a.get(i).getMessage(), a.get(i).getSendTime());
            listChatRes.add(chatRes);
        }
//        a.sort(Comparator.comparingInt(TempChatMessage::getIdx));
//        for(TempChatMessage t : a) {
//            String temp = userRepository.findUserById(t.getSenderName()).orElseThrow(RuntimeException::new).getNickname();
//            ChatRes chatRes = ChatRes.of(temp, t.getMessage(), t.getSendTime());
//            listChatRes.add(chatRes);
//        }


        return ChatRoomRes.of(chatRoomDto.getName(), listChatRes);
    }

    @Transactional
    public void ChatSave(String roomName, String sender, String message) {
        TempChatRoom chatRoom = roomRepository.getById(roomName);
        User user = userRepository.findUserByNickname(sender);
        TempChatMessage chatMessage = TempChatMessage.builder()
                                      .tempChatRoom(chatRoom)
                                      .message(message)
                                      .senderName(user.getId())
                                      .build();

        messageRepository.save(chatMessage);
    }

    @Transactional
    public ChatRoomsRes roomList(String nickName) {
        String userId = userRepository.findUserByNickname(nickName).getId();
        List<TempChatRoom> roomNames1 = roomRepository.findByRoomNameEndsWith(" "+ userId);
        List<TempChatRoom> roomNames2 = roomRepository.findByRoomNameStartsWith(userId + " ");
        List<String> nickList = new ArrayList<>();

        for(TempChatRoom room : roomNames1) {
            String[] a = room.getRoomName().split(" ");
            User temp = userRepository.findUserById(a[0]).orElseThrow(RuntimeException::new);
            nickList.add(temp.getNickname());
        }
        for(TempChatRoom room : roomNames2) {
            String[] a = room.getRoomName().split(" ");
            User temp = userRepository.findUserById(a[1]).orElseThrow(RuntimeException::new);
            nickList.add(temp.getNickname());
        }
        return ChatRoomsRes.of(nickList);
    }
}
