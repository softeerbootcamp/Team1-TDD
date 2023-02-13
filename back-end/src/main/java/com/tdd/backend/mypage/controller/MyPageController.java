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
	@Operation(summary = "해당 Post의 예약현황(날짜) 요청", description = "postId에 해당하는 포스트가 가진 Appointment 리스트로 응답해야 함.")
	public ResponseEntity<MyPageResponse> getAppointments(@LoginUser UserToken userToken) {
		// 응답 :  appointment 리스트
		MyPageResponse myPageInfo = myPageService.getMyPageInfo(userToken.getId());
		return ResponseEntity.ok(myPageInfo);
	}
}
