package com.tdd.backend.mypage.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyCarResponse {
	private final String carName;
	private final Long myCarId;

	@Builder
	private MyCarResponse(String carName, Long myCarId) {
		this.carName = carName;
		this.myCarId = myCarId;
	}
}
