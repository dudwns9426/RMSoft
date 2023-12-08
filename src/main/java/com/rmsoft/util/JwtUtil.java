package com.rmsoft.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	@Value("${jwt.token.secret}")
	private String key;
	
	public static String createToken(String email, SecretKey key, long expireTimsMs) {
		Claims claims = Jwts.claims();
		claims.put("email",email);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTimsMs))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
}
