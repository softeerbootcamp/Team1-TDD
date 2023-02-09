package com.tdd.backend.user;

import org.springframework.stereotype.Service;

import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.util.EncryptHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final EncryptHelper encryptHelper;

	public void save(UserCreate userCreate) {
		String encryptPwd = encryptHelper.encrypt(userCreate.getUserPassword());
		userRepository.save(User.createUser(userCreate, encryptPwd));
	}

	public boolean isDuplicateEmail(String email) {
		return userRepository.countByEmail(email) > 0;
	}

	public String login(UserLogin userLogin) {
		User user = userRepository.findByEmail(userLogin.getEmail())
			.orElseThrow(UserNotFoundException::new);

		if (!encryptHelper.isMatch(userLogin.getUserPassword(), user.getUserPassword())) {
			//todo : Password Not Valid Exception
			throw new UserNotFoundException();
		}

		UserSession userSession = UserSession.builder()
			.user(user)
			.build();

		SessionStorage.addSession(userSession);
		return userSession.getAccessToken();
	}
}
