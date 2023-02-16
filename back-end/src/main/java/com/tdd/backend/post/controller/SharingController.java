package com.tdd.backend.post.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.auth.LoginUser;
import com.tdd.backend.post.data.SharingDto;
import com.tdd.backend.post.service.SharingService;
import com.tdd.backend.user.data.UserToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SharingController {
	private final SharingService sharingService;


	@PostMapping("/sharing")
	@Operation(summary = "차량 공유하기 요청", description = "차량 공유하기 시 Post 정보가 insert 되어야 합니다.")
	@ApiResponse(responseCode = "302", description = "메인 페이지로 REDIRECT")
	public ResponseEntity<Void> shares(@LoginUser UserToken userToken,
		@RequestBody @Valid SharingDto sharingDto) {
		sharingService.save(sharingDto, userToken.getId());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
}
