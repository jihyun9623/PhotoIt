package com.ssafy.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;

import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * jwt 토큰 유틸 정의.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private static String secretKey = "secretKey-test-authorization-jwt-manage-token-photo-it";
    private static Long tokenValidTime =30*60*1000L;
    // 이거 뭐하는 놈인지 꼭 찾아보기;
    private final UserDetailsService userDetailsService;

//    public static final String TOKEN_PREFIX = "Bearer ";
//    public static final String HEADER_STRING = "Authorization";
//    public static final String ISSUER = "ssafy.com";



    // 객체 초기화, secretKey Base64로 인코딩.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //public String createToken(String userPk, UserRole roles){
    public String createToken(String userPk){
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
//
//	public static JWTVerifier getVerifier() {
//        return JWT
//                .require(Algorithm.HMAC512(secretKey.getBytes()))
//                .withIssuer(ISSUER)
//                .build();
//    }
//
//    public static String getToken(String userId) {
//    		Date expires = JwtTokenUtil.getTokenExpiration(tokenValidTime);
//        return JWT.create()
//                .withSubject(userId)
//                .withExpiresAt(expires)
//                .withIssuer(ISSUER)
//                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
//                .sign(Algorithm.HMAC512(secretKey.getBytes()));
//    }
//
//    public static String getToken(Instant expires, String userId) {
//        return JWT.create()
//                .withSubject(userId)
//                .withExpiresAt(Date.from(expires))
//                .withIssuer(ISSUER)
//                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
//                .sign(Algorithm.HMAC512(secretKey.getBytes()));
//    }
//
//    public static Date getTokenExpiration(int expirationTime) {
//    		Date now = new Date();
//    		return new Date(now.getTime() + expirationTime);
//    }
//
//    public static void handleError(String token) {
//        JWTVerifier verifier = JWT
//                .require(Algorithm.HMAC512(secretKey.getBytes()))
//                .withIssuer(ISSUER)
//                .build();
//
//        try {
//            verifier.verify(token.replace(TOKEN_PREFIX, ""));
//        } catch (AlgorithmMismatchException ex) {
//            throw ex;
//        } catch (InvalidClaimException ex) {
//            throw ex;
//        } catch (SignatureGenerationException ex) {
//            throw ex;
//        } catch (SignatureVerificationException ex) {
//            throw ex;
//        } catch (TokenExpiredException ex) {
//            throw ex;
//        } catch (JWTCreationException ex) {
//            throw ex;
//        } catch (JWTDecodeException ex) {
//            throw ex;
//        } catch (JWTVerificationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
//
//    public static void handleError(JWTVerifier verifier, String token) {
//        try {
//            verifier.verify(token.replace(TOKEN_PREFIX, ""));
//        } catch (AlgorithmMismatchException ex) {
//            throw ex;
//        } catch (InvalidClaimException ex) {
//            throw ex;
//        } catch (SignatureGenerationException ex) {
//            throw ex;
//        } catch (SignatureVerificationException ex) {
//            throw ex;
//        } catch (TokenExpiredException ex) {
//            throw ex;
//        } catch (JWTCreationException ex) {
//            throw ex;
//        } catch (JWTDecodeException ex) {
//            throw ex;
//        } catch (JWTVerificationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
}
