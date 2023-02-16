package com.tdd.backend.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.car.data.CarDto;

import lombok.Builder;
import lombok.Getter;

@Table("cars")
@Getter
public class Car {
	@Id
	private Long id;
	private final String carName;
	private final String carKorName;
	private final String carImageUrl;

	@Builder
	private Car(String carName, String carKorName, String carImageUrl) {
		this.carName = carName;
		this.carKorName = carKorName;
		this.carImageUrl = carImageUrl;
	}
	public CarDto teCarDto() {
		return CarDto.builder()
			.carId(this.id)
			.carName(this.carName)
			.carKorName(this.carKorName)
			.carImageUrl(this.carImageUrl)
			.build();
	}
}
