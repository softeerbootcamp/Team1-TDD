package com.tdd.backend.post.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tdd.backend.post.data.DrivingResponse;
import com.tdd.backend.post.data.TesterDto;
import com.tdd.backend.post.service.DrivingService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DrivingController {
	private final DrivingService drivingService;

	@GetMapping("/test-driving/{postId}")
	@Operation(summary = "location 리스트에서 하나의 포스트 선택 시에 대한 요청", description = "포스트의 해당 차종과 옵션 목록으로 응답해야 함.")
	public ResponseEntity<DrivingResponse> sendAllDataByPostId(@PathVariable Long postId) {
		//TODO: pending 확인하고 아니면 다른 응답 보내야함
		DrivingResponse drivingResponse = drivingService.getAllDataByPostId(postId);
		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(drivingResponse);
	}

	@PostMapping("/test-driving/")
	@Operation(summary = "시승가능한 차량 공유에 대한 모든 정보 요청", description = "차종과 가능한 날짜 리스트, 선택한 옵션 리스트를 요청 받으면 해당하는 Post의 관련 모든 정보를 응답함.")
	public void sendSharingDataByOptions() {
		// 요청: 차 이름, 날짜리스트, 옵션 리스트
		// 차이름: 필수
		// 옵션: 모든 옵션을 필수 포함
		// 날짜는 해당하는 경우가 있는 모든
		// 응답 : location 리스트 (postId, locationX, locationY)

	}

	@PatchMapping("/appointments/{appointmentId}")
	@Operation(summary = "최종적인 예약 요청", description = "시승하기에 대한 사용자의 최종적인 요청으로 Appointment의 상태를 승낙으로 Update해야 함.")
	public ResponseEntity<Void> reserveTestDriving(@PathVariable Long appointmentId,
		@RequestBody @Valid TesterDto testerDto) {
		drivingService.approveAppointment(appointmentId, testerDto.getTesterId());
		return ResponseEntity.ok().build();
	}
}
