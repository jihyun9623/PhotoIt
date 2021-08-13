package com.ssafy.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * jwt 토큰 유틸 정의.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static String secretKey = "secretKey-test-authorization-jwt-manage-token-photo-it";
    private static Long tokenValidTime =300*60*1000L;       // 토큰 유효 시간 나중에 바꾸기
    private final UserDetailsService userDetailsService;
    private final StringRedisTemplate redisTemplate;

//    public static final String TOKEN_PREFIX = "Bearer ";
//    public static final String HEADER_STRING = "Authorization";
//    public static final String ISSUER = "ssafy.com";


    // 객체 초기화, secretKey Base64로 인코딩.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //public String createToken(String userPk, UserRole roles){
    public String createToken(@NotNull String id, String role){
//        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
//        claims.put("role", userPk);
        init();
        Date now = new Date();
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                //.setClaims(claims) // 정보 저장
                .setSubject(id)
                .claim("id", id)
                .claim("role", role)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                //.signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                                                                // signature 에 들어갈 secret값 세팅
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
                .compact();
    }


    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        logger.debug("getAuthentication 진입");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserInfo(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserInfo(String token) {
        logger.debug("getUserInfo : 들어온 토큰 = " + token);
        logger.debug("getUserInfo : "+Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
//    public boolean validateToken(String jwtToken) {
//        logger.debug("validateToken 진입");
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//            System.out.println(claims.getBody());
//            return !claims.getBody().getExpiration().before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//
//    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            System.out.println(claims.getBody());
            ValueOperations<String, String>logoutValueOperations=redisTemplate.opsForValue();
            if(logoutValueOperations.get(token)!=null){
                logger.debug("로그아웃된 토큰입니다.");
                return false;
            }
            return !claims.getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public boolean verifyToken(String token){
        if(token == null) return false;
        try {
            String result2 = JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                    .build()
                    .verify(token.replace("Bearer", ""))
                    .getSubject();
            //result
            logger.debug(result2);
            return true;
        }catch(Exception e) {
            logger.error("token값 인증 오류" + e.getMessage());
            return false;
        }
    }

}
