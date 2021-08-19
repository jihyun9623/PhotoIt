package com.ssafy.db.repository;

import com.ssafy.db.entity.TempChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempChatRoomRepository extends JpaRepository<TempChatRoom, String> {
    List<TempChatRoom> findByRoomNameStartsWith(String user);
    List<TempChatRoom> findByRoomNameEndsWith(String user);
}
