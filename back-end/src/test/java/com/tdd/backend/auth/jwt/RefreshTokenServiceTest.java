package com.tdd.backend.auth.jwt;

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
	@DisplayName("RTK Redis에 저장")
	void rtk_save() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtTokenProvider.generateRefreshToken(userId);
		//when
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//then
		RefreshToken rtkFromRedis = refreshTokenService.getRefreshToken(userId);
		Assertions.assertThat(rtkFromRedis.getRefreshToken()).isEqualTo(refreshToken);
	}

}
