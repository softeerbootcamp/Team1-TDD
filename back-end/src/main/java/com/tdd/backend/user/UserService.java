package com.tdd.backend.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.user.data.UserCreate;

@Service
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void save(UserCreate userCreate) {
		userRepository.save(User.createUser(userCreate));
	}
}
