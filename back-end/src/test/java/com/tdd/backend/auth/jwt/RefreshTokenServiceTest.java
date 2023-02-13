package com.tdd.backend.auth.jwt;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.auth.data.RefreshToken;

@SpringBootTest
@Transactional
class RefreshTokenServiceTest {

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Test
	@DisplayName("RTK Redis에 저장 & redis에서 꺼내오기")
	void rtk_save_get() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtTokenProvider.generateRefreshToken(userId);
		//when
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//then
		RefreshToken rtkFromRedis = refreshTokenService.getRefreshToken(userId);
		Assertions.assertThat(rtkFromRedis.getRefreshToken()).isEqualTo(refreshToken);
	}

	@Test
	@DisplayName("rtk 레디스에서 삭제")
	void rtk_delete() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtTokenProvider.generateRefreshToken(userId);
		refreshTokenService.saveRefreshToken(userId, refreshToken);
		//when
		refreshTokenService.deleteRefreshToken(userId);

		//then
		RefreshToken rtkFromRedis = refreshTokenService.getRefreshToken(userId);
		assertThrows(NullPointerException.class, () -> rtkFromRedis.getRefreshToken());
	}

}
