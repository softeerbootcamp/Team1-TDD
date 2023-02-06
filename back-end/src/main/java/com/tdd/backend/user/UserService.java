package com.tdd.backend.user;

import org.springframework.stereotype.Service;

import com.tdd.backend.user.data.UserCreate;

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

}
