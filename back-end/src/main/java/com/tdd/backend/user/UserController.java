package com.tdd.backend.user;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.DuplicateEmailException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@Operation(summary = "유저 회원가입 요청", description = "User SignUp request")
	@PostMapping("/users")
	public ResponseEntity<Void> signup(@RequestBody @Valid UserCreate userCreate) {
		userService.save(userCreate);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@GetMapping("/users/validation/{email}}")
	public void userEmailValidCheck(@PathVariable String email) {
		if (userService.isDuplicateEmail(email)) {
			throw new DuplicateEmailException();
		}
	}

	@Operation(summary = "유저 로그인 요청", description = "User Login request")
	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody @Valid UserLogin userLogin) {
		String accessToken = userService.signIn(userLogin);
		//cookie를 통한 권한 인증
		ResponseCookie cookie = ResponseCookie.from("Session", accessToken)
			.domain("localhost") //추후 yml 파일에 개발환경마다 서비스 도메인 분리
			.path("/")
			.httpOnly(true)
			.secure(false)
			.maxAge(60 * 60)
			.sameSite("Strict")
			.build();

		log.info(">> response cookie : {}", cookie);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create("/"))
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.build();
	}

	@RequestMapping("/test")
	public String test() {
		return "hello world";
	}

	@RequestMapping("/test/auth")
	public void testAuth(@LoginUser UserSession session) {

		User user = session.getUser();
		log.info(">> user : {}", user.getUserName());
		log.info(">> token : {}", session.getAccessToken());
	}
}
