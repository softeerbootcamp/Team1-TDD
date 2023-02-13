package com.tdd.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@Operation(summary = "Refresh Token 재발급 요청", description = "Access Token 만료시의 302 응답에 대해 클라이언트는 RTK를 Authorization 헤더에 담아 전송한다.")
	@PostMapping("/reissue")
	public ResponseEntity<JwtTokenPairResponse> refreshAccessToken(
		@RequestHeader("Authorization") String refreshToken) {
		log.info("> refresh token : {}", refreshToken);
		return ResponseEntity.ok(authService.reIssueToken(refreshToken));
	}

	@Operation(summary = "유저 로그아웃 요청", description = "사용자가 로그아웃을 하면 저장소에서 Refresh Token을 삭제하여 사용이 불가능하도록 한다.")
	@DeleteMapping("/logout")
	public void logout(@RequestHeader("Authorization") String refreshToken) {
		authService.deleteCache(refreshToken);
	}

	@GetMapping("/test/auth")
	public String testAuth(@LoginUser UserToken userToken) {
		return "JWT IS AWESOME " + userToken.getId();
	}
}
