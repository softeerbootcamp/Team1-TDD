package com.tdd.backend.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(UserCreate userCreate) {
		userRepository.save(User.createUser(userCreate));
	}

	public boolean isDuplicateEmail(String email) {
		return userRepository.countByEmail(email) > 0;
	}

	public String signIn(UserLogin userLogin) {
		Optional<User> optionalUser = userRepository.findByEmail(userLogin.getEmail());

		if (optionalUser.isEmpty() || !userLogin.getUserPassword().equals(optionalUser.get().getUserPassword())) {
			throw new UserNotFoundException();
		}
		//todo : 암호화 적용, 세션 암호화
		return optionalUser.get().getUserName();
	}
}
