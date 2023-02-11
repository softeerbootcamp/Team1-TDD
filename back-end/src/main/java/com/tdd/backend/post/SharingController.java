package com.tdd.backend.post;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.post.data.SharingDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SharingController {
	private final SharingService sharingService;
	//TODO: 로그인 검증 필요
	@PostMapping("/sharing")
	@Operation(summary = "차량 공유하기 요청", description = "차량 공유하기 시 Post 정보가 insert 되어야 합니다.")
	@ApiResponse(responseCode = "302", description = "메인 페이지로 REDIRECT")
	public ResponseEntity<Void> shares(@RequestBody @Valid SharingDto sharingDto) {
		// 요청: 차 이름, 위치, 옵션들, 동승여부, 운전경력, 사용자 id , 날짜 리스트,  요구사항
		// 302 REDIRECT index.html
		sharingService.save(sharingDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
}
