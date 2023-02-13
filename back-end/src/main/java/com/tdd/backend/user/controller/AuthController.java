package com.tdd.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.auth.data.JwtTokenPairResponse;
import com.tdd.backend.user.data.UserToken;
import com.tdd.backend.user.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "Refresh Token 재발급 요청", description = "Access Token 만료시 클라이언트는 RTK를 Authorization 헤더에 담아 전송한다.")
	@PostMapping("/reissue")
	public ResponseEntity<JwtTokenPairResponse> refreshAccessToken(
		@RequestHeader("Authorization") String refreshToken) {
		log.info("> refresh token : {}", refreshToken);
		return ResponseEntity.ok(authService.reIssueToken(refreshToken));
	}

	@Operation(summary = "유저의 로그인 유무 판단", description = "클라이언트에서 특정 유저가 로그인 상태인지를 판단할 수 있다.")
	@GetMapping("/auth")
	public ResponseEntity<Void> testAuth(@LoginUser UserToken userToken) {
		return ResponseEntity.ok().build();
	}
}
