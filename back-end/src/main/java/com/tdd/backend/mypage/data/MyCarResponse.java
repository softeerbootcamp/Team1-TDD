package com.tdd.backend.mypage.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyCarResponse {
	private final String carName;
	private final Long mycarId;

	@Builder
	private MyCarResponse(String carName, Long mycarId) {
		this.carName = carName;
		this.mycarId = mycarId;
	}
}
