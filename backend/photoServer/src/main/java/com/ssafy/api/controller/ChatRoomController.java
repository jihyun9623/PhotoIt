package com.ssafy.api.controller;

import com.ssafy.api.request.ChatReq;
import com.ssafy.api.request.ChatUsersReq;
import com.ssafy.api.response.ChatRoomRes;
import com.ssafy.api.response.ChatRoomsRes;
import com.ssafy.api.service.ChatService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.dto.ChatMessageDto;
import com.ssafy.db.dto.ChatRoomDto;
import com.ssafy.db.repository.ChatRoomRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    @ApiOperation(value = "채팅 roomId, 내용 받기 처음들어가면 내용 x")
    @PostMapping("/room")
    @ResponseBody
    public ResponseEntity<ChatRoomRes> createRoom(@RequestBody ChatUsersReq chatUsersReq) {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("createRoom");
        System.out.println("----------------------------------------------------------------------------------------------");
        ChatRoomDto chatRoomDto = chatRoomRepository.createChatRoom(chatUsersReq.getUser1(), chatUsersReq.getUser2());
        ChatRoomRes chatRoomRes = chatService.chatList(chatRoomDto);
        return ResponseEntity.ok(chatRoomRes);
    }

    @ApiOperation(value = "사용자의 채팅 목록 불러오기")
    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<ChatRoomsRes> roomList(@RequestBody ChatReq chatReq) {
        ChatRoomsRes chatRoomsRes = chatService.roomList(chatReq.getSender());
        return ResponseEntity.ok(chatRoomsRes);
    }

    @ApiOperation(value = "채팅")
    @PostMapping("/room/chatting")
    @ResponseBody
    public void roomList(@RequestBody ChatMessageDto message) {
        chatService.ChatSave(message.getRoomName(), message.getSender(), message.getMessage());
    }
}