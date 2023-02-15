package com.tdd.backend.auth.jwt;

import static com.tdd.backend.auth.jwt.JwtProvider.JwtTokenRole.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.auth.data.RefreshToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
@Transactional
class RefreshTokenServiceTest {

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	JwtProvider jwtProvider;

	@Test
	@DisplayName("RTK Redis에 저장 & Redis에서 꺼내오기")
	void rtk_save_get() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtProvider.generateRefreshToken(userId);
		//when
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//then
		RefreshToken rtkFromRedis = refreshTokenService.getRefreshToken(userId);
		Assertions.assertThat(rtkFromRedis.getRefreshToken()).isEqualTo(refreshToken);
	}

	@Test
	@DisplayName("RTK Redis에서 삭제")
	void rtk_delete() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtProvider.generateRefreshToken(userId);
		refreshTokenService.saveRefreshToken(userId, refreshToken);
		//when
		refreshTokenService.deleteRefreshToken(userId);

		//then
		RefreshToken rtkFromRedis = refreshTokenService.getRefreshToken(userId);
		assertThrows(NullPointerException.class, () -> rtkFromRedis.getRefreshToken());
	}

	@Test
	@DisplayName("RTK Redis flush for expired cache")
	void rtk_flush_expired() throws Exception {
		//given
		Long userId = 1L;

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 1);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProvider.getJwtSecret()));

		String refreshToken = Jwts.builder()
			.setSubject(String.valueOf(userId))
			.claim("role", RTK)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(key)
			.compact();
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//when
		refreshTokenService.deleteExpiredRefreshTokens();

		//then
		Assertions.assertThat(refreshTokenService.isRefreshTokenExists(userId)).isFalse();

	}

}
