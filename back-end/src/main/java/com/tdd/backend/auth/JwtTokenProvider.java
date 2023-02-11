package com.tdd.backend.auth;

import static org.apache.tomcat.util.codec.binary.Base64.*;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(String userName) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

		return Jwts.builder()
			.setSubject(userName)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
	}

	public String getUserNameFromJwt(String authToken) {
		Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(decodeBase64(jwtSecret))
			.build()
			.parseClaimsJws(authToken);

		return claims.getBody().getSubject();
	}

	public boolean validateToken(String authToken) {
		try {

			Jwts.parserBuilder()
				.setSigningKey(decodeBase64(jwtSecret))
				.build()
				.parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}
