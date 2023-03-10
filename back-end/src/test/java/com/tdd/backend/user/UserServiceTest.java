package com.tdd.backend.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.auth.encrypt.EncryptHelper;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.repository.UserRepository;
import com.tdd.backend.user.service.UserService;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EncryptHelper encryptHelper;

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
		assertThat(userRepository.findByEmail("tdd@naver.com").get().getUserName()).isEqualTo("tdd");
	}

	@Test
	@DisplayName("유저 로그인_성공")
	void login() throws Exception {
		//given
		User user = User.builder()
			.email("howareyou@test.com")
			.userPassword(encryptHelper.encrypt("pwd"))
			.userName("tester")
			.phoneNumber("01010")
			.createdAt(LocalDate.now())
			.build();
		userRepository.save(user);

		//when
		UserLogin userLogin = UserLogin.builder()
			.email("howareyou@test.com")
			.userPassword("pwd")
			.build();

		userService.login(userLogin);

		//then
		User findUser = userRepository.findByEmail("howareyou@test.com").orElseThrow(UserNotFoundException::new);
		assertThat(findUser.getId()).isEqualTo(user.getId());
	}

	@Test
	@DisplayName("유저 로그인_실패")
	void login_failed() throws Exception {
		//when
		UserLogin userLogin = UserLogin.builder()
			.email("test@test.com")
			.userPassword("pwd")
			.build();

		//expected
		assertThrows(UserNotFoundException.class, () -> userService.login(userLogin));
	}
}
