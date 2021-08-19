package com.ssafy.api.service;

import com.ssafy.api.controller.Usercontroller;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final TempChatMessageRepository messageRepository;
    private final TempChatRoomRepository roomRepository;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Transactional
    public ChatRoomRes chatList(ChatRoomDto chatRoomDto) {
        String sender = userRepository.findUserById(chatRoomDto.getName().split(" ")[0])
                .orElseThrow(RuntimeException::new).getNickname();
        ChatSave(chatRoomDto.getName(), sender, "----------------");
        logger.debug("-----------------------------");
        List<ChatRes> listChatRes = new ArrayList<>();
        TempChatRoom tempChatRoom =  roomRepository.findById(chatRoomDto.getName())
                                     .orElseThrow(RuntimeException::new);
        List<TempChatMessage> a = tempChatRoom.getTempChatMessages();

        if(a!=null) {
            for(TempChatMessage t : a) {
                String temp = userRepository.findUserById(t.getSenderName()).orElseThrow(RuntimeException::new).getNickname();
                ChatRes chatRes = ChatRes.of(temp, t.getMessage(), t.getSendTime());
                listChatRes.add(chatRes);
            }
        }

        return ChatRoomRes.of(chatRoomDto.getName(), listChatRes);
    }

    @Transactional
    public void ChatSave(String roomName, String sender, String message) {
        TempChatRoom chatRoom = roomRepository.getById(roomName);
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        LocalDateTime d = LocalDateTime.parse(format1.format(new Date()),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User user = userRepository.findUserByNickname(sender);
        TempChatMessage chatMessage = TempChatMessage.builder()
                                      .tempChatRoom(chatRoom)
                                      .message(message)
                                      .senderName(user.getId())
                                      .sendTime(d)
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
