package com.tdd.backend.mypage.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.mypage.data.MyCarCreate;
import com.tdd.backend.mypage.service.MyCarService;
import com.tdd.backend.user.data.UserToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MyCarController {
	private final MyCarService myCarService;

	@PostMapping("/mycar/registration")
	@Operation(summary = "내 차 등록하기", description = "유저의 차종, 옵션들을 저장해야 함")
	public ResponseEntity<Void> saveMyCar(
		@LoginUser UserToken userToken,
		@RequestBody @Valid MyCarCreate userCarCreate
	) {
		myCarService.save(userToken.getId(), userCarCreate);
		return ResponseEntity.ok().build();
	}
}
