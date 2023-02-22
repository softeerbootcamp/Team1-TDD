package com.tdd.backend.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.mypage.data.MyPageResponse;
import com.tdd.backend.mypage.service.MyPageService;
import com.tdd.backend.user.data.UserToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;

	@GetMapping("/mypage")
	@Operation(summary = "마이페이지 정보 요청", description = "해당 유저의 정보와 시승, 공유 내용을 모두 응답해야함.")
	public ResponseEntity<MyPageResponse> sendMyPageInfo(@LoginUser UserToken userToken) {
		MyPageResponse myPageInfo = myPageService.getMyPageInfo(userToken.getId());
		return ResponseEntity.ok(myPageInfo);
	}
}
