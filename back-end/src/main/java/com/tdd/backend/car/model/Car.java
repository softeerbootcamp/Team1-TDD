package com.tdd.backend.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("cars")
@Getter
public class Car {
	@Id
	private Long id;
	private final String carName;

	public Car(String carName) {
		this.carName = carName;
	}
}
