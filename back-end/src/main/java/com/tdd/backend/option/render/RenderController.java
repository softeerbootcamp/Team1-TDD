package com.tdd.backend.option.render;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.backend.option.data.OptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RenderController {

	private final RenderService renderService;

	/**
	 * 특정 차종에 대한 모든 옵션 리스트 렌더링
	 */
	@GetMapping("/options/{carName}")
	@Operation(summary = "특정 차종에 대한 모든 옵션 리스트 렌더링", description = "시승, 공유 모두 사용하며, 차종 요청에 대해 가능한 모든 옵션을 제공해야 함.")
	public ResponseEntity<List<OptionResponse>> getOptions(@PathVariable String carName) {
		// 응답: {name}에 존재하는 모든 옵션 리스트 JSON
		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(renderService.getOptions(carName));
	}
}