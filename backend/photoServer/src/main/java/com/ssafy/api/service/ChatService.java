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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final TempChatMessageRepository messageRepository;
    private final TempChatRoomRepository roomRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoomRes chatList(ChatRoomDto chatRoomDto) {
        List<ChatRes> listChatRes = new ArrayList<>();
        TempChatRoom tempChatRoom =  roomRepository.findById(chatRoomDto.getName())
                                     .orElseThrow(RuntimeException::new);

        for(TempChatMessage t : tempChatRoom.getTempChatMessages()) {
            ChatRes chatRes = ChatRes.of(t.getSenderName(), t.getMessage());
            listChatRes.add(chatRes);
        }

        chatRoomRepository.enterChatRoom(tempChatRoom.getRoomName());

        return ChatRoomRes.of(chatRoomDto.getRoomId(), listChatRes);
    }

    @Transactional
    public void ChatSave(String roomId, String sender, String message) {
        TempChatRoom chatRoom = roomRepository.getById(roomId);
        User user = userRepository.findUserByNickname(sender);
        TempChatMessage chatMessage = TempChatMessage.builder()
                                      .tempChatRoom(chatRoom)
                                      .message(message)
                                      .senderName(user.getId())
                                      .build();

        messageRepository.save(chatMessage);
    }

    @Transactional
    public ChatRoomsRes roomList(String JWT) {
        User user = userRepository.findUserById(jwtTokenUtil.getUserPk(JWT)).orElseThrow(RuntimeException::new);

        List<TempChatRoom> roomNames1 = roomRepository.findByRoomNameEndsWith(" "+ user.getId());
        List<TempChatRoom> roomNames2 = roomRepository.findByRoomNameStartsWith(user.getId() + " ");
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
