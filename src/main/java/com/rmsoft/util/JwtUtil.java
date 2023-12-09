package com.rmsoft.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

/**
 * JWT(Json Web Token)를 생성하고 처리하는 유틸리티 클래스입니다.
 * 이 클래스는 JWT를 생성하기 위한 SecretKey를 생성하는 기능을 제공합니다.
 * 또한, 주어진 키로 서명된 JWT를 생성하는 기능도 포함되어 있습니다.
 *
 * @author Jeon Youngjun
 */
@Getter
@Component
public class JwtUtil {

    /** JWT 서명에 사용될 비밀 키 */
    @Value("${jwt.token.secret}")
    private String key;

    /** JWT 만료 시간 (밀리초 단위) */
    private final long expireTimeMs = 1000 * 60 * 60L;

    /**
     * 주어진 키 문자열을 이용하여 SecretKey를 생성합니다.
     *
     * @param key 키 문자열
     * @return 생성된 SecretKey
     */
    public SecretKey secretKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    /**
     * 주어진 이메일, 키, 만료 시간을 이용하여 JWT를 생성합니다.
     *
     * @param email         토큰에 포함될 이메일 정보
     * @param key           서명에 사용될 SecretKey
     * @param expireTimeMs  토큰의 만료 시간 (밀리초 단위)
     * @return 생성된 JWT
     */
    public String createToken(String email, SecretKey key, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}