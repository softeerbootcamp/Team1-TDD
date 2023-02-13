package com.tdd.backend.user.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.JwtTokenPairResponse;
import com.tdd.backend.auth.encrypt.EncryptHelper;
import com.tdd.backend.auth.jwt.JwtTokenProvider;
import com.tdd.backend.auth.jwt.RefreshTokenStorage;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.repository.UserRepository;

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
		LocalDate createdAt = LocalDate.now();
		userRepository.save(User.createUser(userCreate, encryptPwd, createdAt));
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
}
