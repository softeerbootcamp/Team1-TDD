package com.tdd.backend.user;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.JwtTokenPairResponse;
import com.tdd.backend.auth.JwtTokenProvider;
import com.tdd.backend.auth.RefreshTokenStorage;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
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

		String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
		String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
		log.info("> access token : {}", accessToken);
		log.info("> refresh token : {}", refreshToken);

		RefreshTokenStorage.save(refreshToken, user.getEmail());

		return JwtTokenPairResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}
}
