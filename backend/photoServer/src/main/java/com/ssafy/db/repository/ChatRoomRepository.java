package com.ssafy.db.repository;

import com.ssafy.db.dto.ChatRoomDto;
import com.ssafy.db.entity.TempChatRoom;
import com.ssafy.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final UserRepository userRepository;
//    private final RedisMessageListenerContainer redisMessageListener;
//    // 구독 처리 서비스
//    private final RedisSubscriber redisSubscriber;
//    // Redis
//    private static final String CHAT_ROOMS = "CHAT_ROOM";
//    private final RedisTemplate<String, Object> redisTemplate;
//    private HashOperations<String, String, ChatRoomDto> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
//    private Map<String, ChannelTopic> topics;

    private final TempChatRoomRepository roomRepository;

    @PostConstruct
    private void init() {
//        opsHashChatRoom = redisTemplate.opsForHash();
//        topics = new HashMap<>();
    }

    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    @Transactional
    public ChatRoomDto createChatRoom(String user1, String user2) {
        User user1_ = userRepository.findUserByNickname(user1);
        User user2_ = userRepository.findUserByNickname(user2);
        String name;
        if(user1_.getId().compareTo(user2_.getId())>0)
            name = user2_.getId().concat(" "+ user1_.getId());
        else
            name = user1_.getId().concat(" "+user2_.getId());

        ChatRoomDto chatRoom = ChatRoomDto.create(user1_.getId(), user2_.getId());
        TempChatRoom tempChatRoom = TempChatRoom.builder()
                                    .roomName(chatRoom.getName())
                                    .build();
        roomRepository.save(tempChatRoom);
        return chatRoom;
    }

    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
//    public void enterChatRoom(String name) {
//        ChannelTopic topic = topics.get(name);
//        if (topic == null) {
//            topic = new ChannelTopic(name);
//            redisMessageListener.addMessageListener(redisSubscriber, topic);
//            topics.put(name, topic);
//        }
//    }

//    public ChannelTopic getTopic(String roomId) {
//        return topics.get(roomId);
//    }
}