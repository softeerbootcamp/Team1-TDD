package com.tdd.backend.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostCotroller {
	private final PostService postService;

	@Operation(summary = "차량 공유하기 요청", description = "차량 공유하기 시 Post 정보가 insert 되어야 합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "302", description = "메인 페이지로 REDIRECT"),
		@ApiResponse(responseCode = "404", description = "NOT FOUND"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("/shares")
	public void shares() {

	}
}
