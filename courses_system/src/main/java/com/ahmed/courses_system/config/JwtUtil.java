package com.ahmed.courses_system.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

	private final String secret = "mySecretKeyThatMustBeAtLeast256BitsLongForSecurity";

	private SecretKey getKey(){
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(String userName, String userType){
		return Jwts.builder()
				.subject(userName)
				.claim("userType", userType)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(getKey())
				.compact();
	}

	public String extractUsername(String token){
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public String extractUserType(String token){
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.get("userType", String.class);
	}

	public boolean isTokenValid(String token){
		try {
			Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

}
