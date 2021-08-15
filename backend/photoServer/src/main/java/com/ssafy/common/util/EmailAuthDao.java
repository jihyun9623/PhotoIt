package com.ssafy.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class EmailAuthDao {
    private final String PREFIX = "email:";  // key값이 중복되지 않도록 상수 선언
    private final int LIMIT_TIME = 5 * 60;  // Redis에서 해당 데이터의 유효시간(TTL) 설정

    @Autowired
    private StringRedisTemplate stringRedisTemplate;        // redis template

    // 사용자가 인증할 아이디(=이메일)과 인증코드를 저장하고, TTL은 5분으로 설정
    public void createEmailAuth(String id, String code){
        stringRedisTemplate.opsForValue().set(PREFIX+id, code, Duration.ofSeconds(LIMIT_TIME));
    }

    // Redis에서 아이디(=이메일)에 해당하는 인증코드 리턴
   public String getEmailAuth(String id){
        return stringRedisTemplate.opsForValue().get(PREFIX+id);
    }

    // 인증이 완료되었다면 메모리 관리를 위해 Redis에 저장된 인증번호 삭제
    public void removeEmailAuth(String id){
        stringRedisTemplate.delete(PREFIX+id);
    }

    // Redis에 해당 id로 저장된 인증코드가 존재하는지 확인인
    public boolean hasKey(String id){
        return stringRedisTemplate.hasKey(PREFIX+id);
    }

}
