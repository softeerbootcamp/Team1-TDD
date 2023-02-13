package com.tdd.backend.user.service;

import static com.tdd.backend.auth.jwt.JwtTokenProvider.JwtTokenRole.*;
import static com.tdd.backend.auth.jwt.JwtTokenProvider.JwtTokenStatus.*;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.JwtTokenPairResponse;
import com.tdd.backend.auth.exception.InvalidTokenException;
import com.tdd.backend.auth.jwt.JwtTokenProvider;
import com.tdd.backend.auth.jwt.RefreshTokenService;
import com.tdd.backend.auth.jwt.RefreshTokenStorage;
import com.tdd.backend.user.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenService refreshTokenService;

	// TODO : RTK도 만료시 재로그인 요청 보내야함 InvalidToken이랑 다름.
	public JwtTokenPairResponse reIssueToken(String refreshToken) {
		//리프레쉬 토큰이 validate 하고 유효한 RTK라면, 새로운 ATK 재발급
		if (jwtTokenProvider.validateToken(refreshToken) == ACCESS) {
			if (!jwtTokenProvider.getRoleFromJwt(refreshToken).equals(RTK)) {
				throw new InvalidTokenException();
			}
			// ATK 재발급은 RTK의 payload에서 유저의 id를 꺼낸 뒤, Redis 인메모리에 해당 유저의 존재 유무로 결정된다.
			Long id = jwtTokenProvider.getUserIdFromJwt(refreshToken);

			//TODO : 이론적으로 인메모리에 해당하는 key (email) 이 없는 경우에 대한 방식이 적절한 지 판단
			if (!RefreshTokenStorage.isValidateUserId(id)) {
				throw new UnauthorizedException();
			}

			String newAccessToken = jwtTokenProvider.generateAccessToken(id);
			String newRefreshToken = jwtTokenProvider.generateRefreshToken(id);
			log.info(">> reissued access token : {}", newAccessToken);
			log.info(">> reissued refresh token : {}", newRefreshToken);

			return JwtTokenPairResponse.builder()
				.accessToken(newAccessToken)
				.refreshToken(newRefreshToken)
				.build();
		}
		throw new UnauthorizedException();
	}

	public void deleteCache(String refreshToken) {
		RefreshTokenStorage.deleteCache(refreshToken);
	}
}
