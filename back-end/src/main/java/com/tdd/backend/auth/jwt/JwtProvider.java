package com.tdd.backend.auth.jwt;

import static com.tdd.backend.auth.jwt.JwtProvider.JwtRole.*;
import static org.apache.tomcat.util.codec.binary.Base64.*;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tdd.backend.auth.exception.ExpiredATKException;
import com.tdd.backend.user.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class JwtProvider {

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expirationInMs}")
	private int jwtExpirationInMs;

	@Value("${app.jwt.refreshExpirationInMs}")
	private int jwtRefreshExpirationInMs;

	public String generateAccessToken(Long id, String email) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

		return Jwts.builder()
			.setSubject(String.valueOf(id))
			.claim("email", email)
			.claim("role", ATK)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
	}

	public String generateRefreshToken(Long id, String email) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtRefreshExpirationInMs);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

		return Jwts.builder()
			.setSubject(String.valueOf(id))
			.claim("email", email)
			.claim("role", RTK)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
	}

	public Long getUserIdFromJwt(String authToken) {
		Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(decodeBase64(jwtSecret))
			.build()
			.parseClaimsJws(authToken);

		return Long.parseLong(claims.getBody().getSubject());
	}

	public String getEmailFromJwt(String authToken) {
		Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(decodeBase64(jwtSecret))
			.build()
			.parseClaimsJws(authToken);

		return claims.getBody().get("email", String.class);
	}

	public JwtRole getRoleFromJwt(String authToken) {
		Jws<Claims> claims = Jwts.parserBuilder()
			.setSigningKey(decodeBase64(jwtSecret))
			.build()
			.parseClaimsJws(authToken);

		return JwtRole.valueOf(claims.getBody().get("role", String.class));
	}

	public boolean isValidateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(decodeBase64(jwtSecret)).build().parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			log.info(">> Access Token is expired! please redirect to POST /reissue to regenerate new access token");
			throw new ExpiredATKException();
		} catch (JwtException | IllegalArgumentException e) {
			log.error("jwtException : {}", e.getMessage());
		}
		throw new UnauthorizedException();
	}

	public enum JwtRole {
		ATK, RTK
	}
}
