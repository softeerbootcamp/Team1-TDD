package com.tdd.backend.user.service;

import static com.tdd.backend.auth.jwt.JwtProvider.JwtRole.*;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.JwtTokenPairResponse;
import com.tdd.backend.auth.exception.InvalidTokenException;
import com.tdd.backend.auth.jwt.JwtProvider;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtProvider jwtProvider;

	public JwtTokenPairResponse issueToken(User user) {
		String accessToken = jwtProvider.generateAccessToken(user.getId());
		String refreshToken = jwtProvider.generateRefreshToken(user.getId());
		log.info("> access token : {}", accessToken);
		log.info("> refresh token : {}", refreshToken);

		return JwtTokenPairResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	// TODO : RTK도 만료시 재로그인 요청 보내야함 InvalidToken이랑 다름.
	public JwtTokenPairResponse reIssueToken(String refreshToken) {
		//리프레쉬 토큰이 validate 하고 유효한 RTK라면, 새로운 ATK 재발급
		if (jwtProvider.isValidateToken(refreshToken)) {
			if (!jwtProvider.getRoleFromJwt(refreshToken).equals(RTK)) {
				throw new InvalidTokenException();
			}
			// TODO : ATK 재발급은 RTK의 payload에서 유저의 id를 꺼내며, 꺼내지는 지에 대한 Validation 처리 필요
			// throw new UnauthorizedException();
			Long id = jwtProvider.getUserIdFromJwt(refreshToken);

			String newAccessToken = jwtProvider.generateAccessToken(id);
			String newRefreshToken = jwtProvider.generateRefreshToken(id);
			log.info(">> reissued access token : {}", newAccessToken);
			log.info(">> reissued refresh token : {}", newRefreshToken);

			return JwtTokenPairResponse.builder()
				.accessToken(newAccessToken)
				.refreshToken(newRefreshToken)
				.build();
		}
		throw new UnauthorizedException();
	}
}
