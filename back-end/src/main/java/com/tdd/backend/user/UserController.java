package com.tdd.backend.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.JwtTokenProvider;
import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.TokenResponse;
import com.tdd.backend.user.data.UserCreate;
import com.tdd.backend.user.data.UserLogin;
import com.tdd.backend.user.data.UserToken;
import com.tdd.backend.user.exception.DuplicateEmailException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;

	@Operation(summary = "유저 회원가입 요청", description = "User SignUp request")
	@PostMapping("/users")
	public ResponseEntity<Void> signup(@RequestBody @Valid UserCreate userCreate) {
		userService.save(userCreate);

		log.info("> user {} saved", userCreate.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/users/validation/{email}}")
	public void userEmailValidCheck(@PathVariable String email) {
		if (userService.isDuplicateEmail(email)) {
			throw new DuplicateEmailException();
		}
	}

	@Operation(summary = "유저 로그인 요청", description = "User Login request")
	@PostMapping("/login")
	public TokenResponse login(@RequestBody @Valid UserLogin userLogin) {

		String jws = jwtTokenProvider.generateToken(userService.login(userLogin));

		log.info("> access token : {}", jws);
		return new TokenResponse(jws);
	}

	@GetMapping("/test/auth")
	public String testAuth(@LoginUser UserToken userToken) {
		return "JWT IS AWESOME " + userToken.getUserName();
	}
}
