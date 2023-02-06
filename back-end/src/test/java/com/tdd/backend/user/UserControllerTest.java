package com.tdd.backend.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.backend.user.data.UserCreate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	UserController userController;
	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setup() {
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("유저 회원가입")
	void signup() throws Exception {
		//given
		UserCreate userCreate = UserCreate.builder()
			.email("test@tdd.com")
			.userPassword("pass")
			.userName("tester")
			.phoneNumber("0101010")
			.build();

		String jsonRequest = objectMapper.writeValueAsString(userCreate);

		//when
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().isOk())
			.andDo(print());

		//then

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(userRepository.count()).isEqualTo(1);

		User user = userRepository.findByEmail(userCreate.getEmail())
			.orElseThrow(() -> new RuntimeException("해당 이메일의 유저 존재하지 않습니다."));
		softAssertions.assertThat(user.getUserName()).isEqualTo(userCreate.getUserName());

		softAssertions.assertAll();
	}

}
