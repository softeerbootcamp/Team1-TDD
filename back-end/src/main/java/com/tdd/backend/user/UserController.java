package com.tdd.backend.user;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.exception.DuplicateEmailException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

	@Value("${domain.address}")
	private String domainAddress; //개발환경에 따른 도메인 주소를 yml에 파일변수로 세팅

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

		//todo: cookie를 통한 권한 인증, 다른 방식의 인증에 대해 리팩토링 여지 있음.
		ResponseCookie cookie = ResponseCookie.from("Session", accessToken)
			.domain(domainAddress) //yml 파일에 개발환경마다 서비스 도메인 분리
			.path("/")
			.httpOnly(true)
			.secure(false)
			.maxAge(60 * 60)
			.sameSite("Lax")
			.build();

		log.info(">> response cookie : {}", cookie);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create("/"))
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.build();
	}
}
