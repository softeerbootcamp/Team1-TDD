package com.tdd.backend.auth;

import static com.tdd.backend.auth.JwtTokenProvider.JwtTokenRole.*;
import static com.tdd.backend.auth.JwtTokenProvider.JwtTokenStatus.*;
import static org.apache.tomcat.util.codec.binary.Base64.*;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expirationInMs}")
	private int jwtExpirationInMs;

	@Value("${app.jwt.refreshExpirationInMs}")
	private int jwtRefreshExpirationInMs;

	public String generateAccessToken(String email) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

		return Jwts.builder()
			.setSubject(email)
			.claim("role", ATK)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
	}

	public String generateRefreshToken(String email) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtRefreshExpirationInMs);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

		return Jwts.builder()
			.setSubject(email)
			.claim("role", ATK)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
	}

	public String getEmailFormJwt(String authToken) {
		Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(decodeBase64(jwtSecret))
			.build()
			.parseClaimsJws(authToken);

		return claims.getBody().getSubject();
	}

	public JwtTokenStatus validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(decodeBase64(jwtSecret)).build().parseClaimsJws(authToken);
			return ACCESS;
		} catch (ExpiredJwtException e) {
			// 만료된 경우에는 refresh token을 확인하기 위해
			return EXPIRED;
		} catch (JwtException | IllegalArgumentException e) {
			log.error("jwtException : {}", e.getMessage());
		}
		return DENIED;
	}

	public enum JwtTokenRole {
		ATK, RTK
	}

	public enum JwtTokenStatus {
		DENIED, ACCESS, EXPIRED
	}
}
