//package com.ssafy.api.controller;
//
//// import 생략...
//
//import com.ssafy.api.service.ChatService;
//import com.ssafy.common.chat.RedisPublisher;
//import com.ssafy.db.dto.ChatMessageDto;
//import com.ssafy.db.entity.User;
//import com.ssafy.db.repository.ChatRoomRepository;
//import com.ssafy.db.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@CrossOrigin(origins="http://localhost:8081/", allowedHeaders="*", allowCredentials = "true")
//public class ChatController {
//
//    private final RedisPublisher redisPublisher;

//    private final ChatRoomRepository chatRoomRepository;
//    private final ChatService chatService;
//
//    /**
//     * websocket ""로 들어오는 메시징을 처리한다.
//     */
//    @MessageMapping("/chat/message")
//    public void message(ChatMessageDto message) {
//        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
//        System.out.println("왓나??");
//        chatService.ChatSave(message.getRoomName(), message.getSender(), message.getMessage());
//
//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomName()), message);
//    }
//}