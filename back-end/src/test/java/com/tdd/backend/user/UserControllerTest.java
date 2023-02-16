package com.tdd.backend.user;

import static com.tdd.backend.auth.jwt.JwtProvider.JwtTokenRole.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
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
import com.tdd.backend.auth.encrypt.EncryptHelper;
import com.tdd.backend.auth.jwt.JwtProvider;
import com.tdd.backend.auth.jwt.RefreshTokenService;
import com.tdd.backend.user.controller.UserController;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
	JwtProvider jwtProvider;

	@Autowired
	RefreshTokenService refreshTokenService;

	@BeforeEach
	void setup() {
		refreshTokenService.deleteAll();
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
			.orElseThrow(UserNotFoundException::new);
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
			.createdAt(LocalDate.now())
			.build();
		userRepository.save(user);

		//when
		mockMvc.perform(get("/users/validation/{email}", user.getEmail())
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
			.createdAt(LocalDate.now())
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
			.andExpect(jsonPath("$.accessToken").isNotEmpty())
			.andExpect(jsonPath("$.refreshToken").isNotEmpty())
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
			.createdAt(LocalDate.now())
			.build();

		userRepository.save(user);
		String jws = jwtProvider.generateAccessToken(user.getId());

		//expected
		mockMvc.perform(get("/auth")
				.header("Authorization", jws)
			)
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("인증되지 않은 접근")
	void access_non_auth() throws Exception {

		//expected
		mockMvc.perform(get("/auth")
				.contentType(MediaType.TEXT_PLAIN)
			)
			.andExpect(status().isUnauthorized())
			.andExpect(jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.toString()))
			.andExpect(jsonPath("$.errorMessage").value("인증되지 않은 접근입니다."))
			.andDo(print());

	}

	@Test
	@DisplayName("ATK 만료시 RTK로 재발급")
	void reissue_ATK() throws Exception {
		String email = "test@test.com";
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 1);
		String accessToken = Jwts.builder()
			.setSubject(email)
			.claim("role", ATK)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProvider.getJwtSecret())))
			.compact();

		mockMvc.perform(get("/auth")
				.header("Authorization", accessToken)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.code").value(FOUND.toString()))
			.andExpect(jsonPath("$.errorMessage").value("토큰이 만료되었습니다. 재발급해주세요."))
			.andDo(print());

	}

	@Test
	@DisplayName("ATK만료, RTK 유효시 토큰 재발급")
	void validate_RTK_expire_ATK() throws Exception {
		//when
		Long userId = 1L;
		String refreshToken = jwtProvider.generateRefreshToken(userId);
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//expected
		mockMvc.perform(post("/reissue")
				.header("Authorization", refreshToken)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.accessToken").isNotEmpty())
			.andExpect(jsonPath("$.refreshToken").isNotEmpty())
			.andDo(print());
	}

	@Test
	@DisplayName("ATK와 RTK 모두 만료시 재로그인")
	void expired_RTK() throws Exception {
		Long userId = 1L;
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 1);
		String refreshToken = Jwts.builder()
			.setSubject(String.valueOf(userId))
			.claim("role", RTK)
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProvider.getJwtSecret())))
			.compact();

		mockMvc.perform(post("/reissue")
				.header("Authorization", refreshToken)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isFound())
			.andDo(print());
	}

	@Test
	@DisplayName("로그아웃 시 RTK 스토리지에서 해당 key-value 쌍 삭제")
	void logout_RTK_remove() throws Exception {
		//given
		Long userId = 1L;
		String refreshToken = jwtProvider.generateRefreshToken(userId);
		refreshTokenService.saveRefreshToken(userId, refreshToken);

		//expected
		mockMvc.perform(delete("/logout")
				.header("Authorization", refreshToken)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andDo(print());

		assertThat(refreshTokenService.isRefreshTokenExists(userId)).isFalse();

	}

	@Test
	@DisplayName("인증 시 ATK가 아닐 경우 Exception 발생")
	void check_validate_ATK() throws Exception {
		//given
		Long userId = 1L;
		String rtk = jwtProvider.generateRefreshToken(userId);

		//expected
		mockMvc.perform(get("/auth")
				.header("Authorization", rtk)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isBadRequest())
			.andDo(print());

	}

	@Test
	@DisplayName("reissue 시 RTK가 아닐 경우 Exception 발생")
	void check_validate_RTK() throws Exception {
		//given
		Long userId = 1L;
		String atk = jwtProvider.generateAccessToken(userId);

		//expected
		mockMvc.perform(post("/reissue")
				.header("Authorization", atk)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isBadRequest())
			.andDo(print());

	}
}
