package com.tdd.backend.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.JwtTokenPairResponse;
import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.auth.RefreshTokenStorage;
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
	public ResponseEntity<JwtTokenPairResponse> login(@RequestBody @Valid UserLogin userLogin) {
		return ResponseEntity.ok(userService.login(userLogin));
	}

	//TODO : 역할에 따라 토큰 서비스 클래스 분리

	// RTK이며, 요청이 POST /reissue인 경우 재발급을 진행한다.
	@PostMapping("/reissue")
	public ResponseEntity<JwtTokenPairResponse> refreshAccessToken(
		@RequestHeader("Authorization") String refreshToken) {
		log.info("> refresh token : {}", refreshToken);
		return ResponseEntity.ok(userService.reIssueToken(refreshToken));
	}

	//사용자가 로그아웃을 하면 저장소에서 Refresh Token을 삭제하여 사용이 불가능하도록 한다.
	//todo : redirect??
	@DeleteMapping("/logout")
	public void logout(@LoginUser UserToken userToken) {
		RefreshTokenStorage.deleteCache(userToken.getId());
	}

	@GetMapping("/test/auth")
	public String testAuth(@LoginUser UserToken userToken) {
		return "JWT IS AWESOME " + userToken.getId();
	}
}
