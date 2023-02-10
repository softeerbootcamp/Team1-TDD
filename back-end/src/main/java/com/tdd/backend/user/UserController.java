package com.tdd.backend.user;

import java.net.URI;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.SessionResponse;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.DuplicateEmailException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	// @Value("${jwt.token.secret-key:secret-key}") //jwt secret key (yml 파일변수 세팅)
	private final String KEY = "JFbPbHB/8Oz2CSK4q0sAHrRkr4Hs9MYwKkMY4Jf97+0=";

	@Operation(summary = "유저 회원가입 요청", description = "User SignUp request")
	@PostMapping("/users")
	public ResponseEntity<Void> signup(@RequestBody @Valid UserCreate userCreate) {
		userService.save(userCreate);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@GetMapping("/users/validation/{email}}")
	public void userEmailValidCheck(@PathVariable String email) {
		if (userService.isDuplicateEmail(email)) {
			throw new DuplicateEmailException();
		}
	}

	@Operation(summary = "유저 로그인 요청", description = "User Login request")
	@PostMapping("/login")
	public SessionResponse login(@RequestBody @Valid UserLogin userLogin) {
		Long userId = userService.login(userLogin);

		SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
		String jws = Jwts.builder()
			.setSubject(String.valueOf(userId))
			.signWith(key)
			.compact();

		return new SessionResponse(jws);
	}

	@GetMapping("/test/auth")
	public String testAuth(@LoginUser UserSession userSession) {
		return "JWT IS AWESOME";
	}
}
