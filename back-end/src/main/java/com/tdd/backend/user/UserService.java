package com.tdd.backend.user;

import static com.tdd.backend.auth.JwtTokenProvider.JwtTokenRole.*;
import static com.tdd.backend.auth.JwtTokenProvider.JwtTokenStatus.*;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.InvalidTokenException;
import com.tdd.backend.auth.JwtTokenPairResponse;
import com.tdd.backend.auth.JwtTokenProvider;
import com.tdd.backend.auth.RefreshTokenStorage;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.UnauthorizedException;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.util.EncryptHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final EncryptHelper encryptHelper;
	private final JwtTokenProvider jwtTokenProvider;

	public void save(UserCreate userCreate) {
		String encryptPwd = encryptHelper.encrypt(userCreate.getUserPassword());
		userRepository.save(User.createUser(userCreate, encryptPwd));
	}

	public boolean isDuplicateEmail(String email) {
		return userRepository.countByEmail(email) > 0;
	}

	public JwtTokenPairResponse login(UserLogin userLogin) {
		User user = userRepository.findByEmail(userLogin.getEmail())
			.orElseThrow(UserNotFoundException::new);

		if (!encryptHelper.isMatch(userLogin.getUserPassword(), user.getUserPassword())) {
			throw new UserNotFoundException();
		}

		String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
		String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());
		log.info("> access token : {}", accessToken);
		log.info("> refresh token : {}", refreshToken);

		RefreshTokenStorage.save(user.getId(), refreshToken);

		return JwtTokenPairResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public JwtTokenPairResponse reIssueToken(String refreshToken) {
		//리프레쉬 토큰이 validate 하다면 새로운 ATK 재발급
		if (!jwtTokenProvider.getRoleFromJwt(refreshToken).equals(RTK)) {
			throw new InvalidTokenException();
		}


		if (jwtTokenProvider.validateToken(refreshToken) == ACCESS) {
			// ATK 재발급은 RTK의 payload에서 유저의 email을 꺼낸 뒤, Redis 인메모리에 해당 유저의 존재 유무로 결정된다.
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
}
