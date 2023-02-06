package com.tdd.backend.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setup() {
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("유저 저장")
	void save() {

		userService.save(new User("hado@naver.com", "young",
			"01012341234", "glory"));
		Assertions.assertThat(userRepository.count()).isEqualTo(1);
	}
}
