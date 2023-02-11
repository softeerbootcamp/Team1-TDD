package com.tdd.backend.post;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tdd.backend.post.data.SharingDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DrivingController {
	private final DrivingService drivingService;

	@GetMapping("/test-driving/locations")
	@Operation(summary = "시승가능한 차량 요청", description = "차종과 선택한 옵션 리스트를 요청 받으면 해당하는 Post의 location 리스트로 응답해야 함.")
	public void sendSharingDataByOptions() {
		// 요청: 차 이름, 날짜리스트, 옵션 리스트
		// 차이름: 필수
		// 옵션: 모든 옵션을 필수 포함
		// 날짜는 해당하는 경우가 있는 모든
		// 응답 : location 리스트 (postId, locationX, locationY)

	}

	@GetMapping("/test-driving/{postId}")
	@Operation(summary = "location 리스트에서 하나의 포스트 선택 시에 대한 요청", description = "포스트의 해당 차종과 옵션 목록으로 응답해야 함.")
	public ResponseEntity<SharingDto> sendSharingDataByPostId(@PathVariable Long postId) {
		//TODO: pending 확인하고 아니면 다른 응답 보내야함
		SharingDto sharingDto = drivingService.getSharingDateByPostId(postId);
		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(sharingDto);
	}

	@GetMapping("/test-driving/appointments/{postId}")
	@Operation(summary = "해당 Post의 예약현황(날짜) 요청", description = "postId에 해당하는 포스트가 가진 Appointment 리스트로 응답해야 함.")
	public void getAppointments(@PathVariable Long postId) {
		// 응답 :  appointment 리스트
	}
}
