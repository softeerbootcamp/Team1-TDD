package com.tdd.backend.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.user.request.UserCreate;

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
		//given
		UserCreate userCreate = UserCreate.builder()
			.email("tdd@naver.com")
			.userPassword("glory")
			.userName("tdd")
			.phoneNumber("01012341234")
			.build();

		//when
		userService.save(userCreate);

		//then
		Assertions.assertThat(userRepository.count()).isEqualTo(1);
	}
}
