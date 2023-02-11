package com.tdd.backend.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.backend.auth.JwtTokenProvider;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.util.EncryptHelper;

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

	@Autowired
	EncryptHelper encryptHelper;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

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
			.orElseThrow(RuntimeException::new);
		softAssertions.assertThat(user.getUserName()).isEqualTo(userCreate.getUserName());

		softAssertions.assertAll();
	}

	@Test
	@DisplayName("유저 회원가입_null 값 들어옴.")
	void signup_null() throws Exception {
		//given
		UserCreate userCreate = UserCreate.builder()
			.email(null)
			.userPassword("pass")
			.userName("tester")
			.phoneNumber("0101010")
			.build();

		String jsonRequest = objectMapper.writeValueAsString(userCreate);

		//expected
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.toString()))
			.andExpect(jsonPath("$.errorMessage").value("잘못된 요청입니다."))
			.andExpect(jsonPath("$.validation.email").value("email 값은 필수입니다."))
			.andDo(print());
	}

	@Test
	@DisplayName("유저 회원가입_중복 이메일 들어옴.")
	void signup_duplicate_email() throws Exception {
		//given
		User user = User.builder()
			.email("test@test.com")
			.userName("tester")
			.userPassword("pwd")
			.phoneNumber("101010")
			.build();
		userRepository.save(user);

		//when
		mockMvc.perform(get("/users/validation/{email}}", user.getEmail())
				.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.toString()))
			.andExpect(jsonPath("$.errorMessage").value("중복된 이메일입니다."))
			.andDo(print());
	}

	@Test
	@DisplayName("유저 로그인_성공")
	void login() throws Exception {
		//given
		userRepository.save(User.builder()
			.email("test@test.com")
			.userPassword(encryptHelper.encrypt("pwd"))
			.userName("tester")
			.phoneNumber("010101")
			.build()
		);

		//when
		UserLogin userLogin = UserLogin.builder()
			.email("test@test.com")
			.userPassword("pwd")
			.build();
		String loginRequestBody = objectMapper.writeValueAsString(userLogin);

		//then
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginRequestBody))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("유저 로그인_실패")
	void login_failed() throws Exception {
		//when
		UserLogin userLogin = UserLogin.builder()
			.email("test@test.com")
			.userPassword("pwd")
			.build();

		String loginRequestBody = objectMapper.writeValueAsString(userLogin);
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginRequestBody))
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.errorMessage").value("해당하는 유저가 없습니다."))
			.andDo(print());
	}

	@Test
	@DisplayName("유저 로그인 유지 (jwt)")
	void login_jwt() throws Exception {
		//when
		User user = User.builder()
			.email("test@test.com")
			.userPassword(encryptHelper.encrypt("pwd"))
			.userName("tester")
			.phoneNumber("010101")
			.build();

		userRepository.save(user);
		String jws = jwtTokenProvider.generateAccessToken(user.getUserName());

		//expected
		mockMvc.perform(get("/test/auth")
				.header("Authorization", jws)
			)
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	@DisplayName("인증되지 않은 접근 테스트")
	void access_non_auth() throws Exception {
	    //when
		String invalidToken = "aabdakflafklanl";
	    //expected
		mockMvc.perform(get("/test/auth")
				.header("Authorization", invalidToken)
			)
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.toString()))
			.andExpect(jsonPath("$.errorMessage").value("인증되지 않은 접근입니다."))
			.andDo(print());
	    
	}
}
