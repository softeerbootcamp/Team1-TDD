package com.tdd.backend.car.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CarDto {
	private final String carName;
	private final String carKorName;
	private final String carImageUrl;

	@Builder
	private CarDto(String carName, String carKorName, String carImageUrl) {
		this.carName = carName;
		this.carKorName = carKorName;
		this.carImageUrl = carImageUrl;
	}
}
