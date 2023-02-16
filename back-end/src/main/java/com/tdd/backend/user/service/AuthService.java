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
		String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getEmail());
		String refreshToken = jwtProvider.generateRefreshToken(user.getId(), user.getEmail());
		log.info("> access token : {}", accessToken);
		log.info("> refresh token : {}", refreshToken);

		return JwtTokenPairResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public JwtTokenPairResponse reIssueToken(String refreshToken) {
		//리프레쉬 토큰이 validate 하고 유효한 RTK라면, 새로운 ATK 재발급
		if (jwtProvider.isValidateToken(refreshToken)) {
			if (!jwtProvider.getRoleFromJwt(refreshToken).equals(RTK)) {
				throw new InvalidTokenException();
			}

			Long id = jwtProvider.getUserIdFromJwt(refreshToken);
			String email = jwtProvider.getEmailFromJwt(refreshToken);

			String newAccessToken = jwtProvider.generateAccessToken(id, email);
			String newRefreshToken = jwtProvider.generateRefreshToken(id, email);
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
