package com.tdd.backend.user.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.JwtTokenPairResponse;
import com.tdd.backend.auth.encrypt.EncryptHelper;
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
	private final AuthService authService;

	public void save(UserCreate userCreate) {
		String encryptPwd = encryptHelper.encrypt(userCreate.getUserPassword());
		LocalDate createdAt = LocalDate.now();
		userRepository.save(User.createUser(userCreate, encryptPwd, createdAt));
	}

	public boolean isDuplicateEmail(String email) {
		return userRepository.countByEmail(email) > 0;
	}

	public JwtTokenPairResponse login(UserLogin userLogin) {
		//유저 인증 (디비)
		User user = userRepository.findByEmail(userLogin.getEmail())
			.orElseThrow(UserNotFoundException::new);

		if (!encryptHelper.isMatch(userLogin.getUserPassword(), user.getUserPassword())) {
			throw new UserNotFoundException();
		}

		// 토큰 쌍 발급하여 응답
		return authService.issueToken(user);
	}
}
