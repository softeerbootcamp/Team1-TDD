package com.tdd.backend.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.user.data.UserSession;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DummyController {
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
}
