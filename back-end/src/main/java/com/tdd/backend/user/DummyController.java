package com.tdd.backend.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.data.UserSession;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DummyController {

	@Value("${domain.address}")
	private String domainAddress; //개발환경에 따른 도메인 주소를 yml에 파일변수로 세팅

	private final UserService userService;

	@Operation(summary = "CORS 테스트를 위한 더미 API", description = "인증 필요없이 접근 가능")
	@GetMapping("/test")
	public String test() {
		return "hello world";
	}

	@Operation(summary = "CORS 테스트를 위한 더미 API (인증필요)", description = "인증이 요구되는 접근 (쿠키 필요)")
	@GetMapping("/test/auth")
	public void testAuth(@LoginUser UserSession session) {

		User user = session.getUser();
		log.info(">> user : {}", user.getUserName());
		log.info(">> token : {}", session.getAccessToken());
	}

	@Operation(summary = "CORS 테스트를 위한 더미 API (쿠키 발급)", description = "쿠키를 테스트하기 위한 쿠키 발급 테스트")
	@GetMapping("/test/cookie")
	public ResponseEntity<ResponseCookie> testCookie() {
		UserCreate dummyUser = UserCreate.builder()
			.email("test@test.com")
			.userPassword("password")
			.userName("육식주의자")
			.phoneNumber("01001010101")
			.build();
		userService.save(dummyUser);

		UserLogin userLogin = UserLogin.builder()
			.email("test@test.com")
			.userPassword("password")
			.build();
		String token = userService.signIn(userLogin);

		//cookie를 통한 권한 인증
		ResponseCookie cookie = ResponseCookie.from("Session", token)
			.domain(domainAddress) //추후 yml 파일에 개발환경마다 서비스 도메인 분리
			.path("/")
			.httpOnly(true)
			.secure(false)
			.maxAge(60 * 60)
			.sameSite("Strict")
			.build();

		log.info(">> {}", token);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create("/"))
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.build();
	}
}
