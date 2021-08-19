package com.ssafy.db.repository;

import com.ssafy.db.entity.TempChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempChatMessageRepository extends JpaRepository<TempChatMessage, Integer> {
}
