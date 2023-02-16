package com.tdd.backend.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("cars")
@Getter
public class Car {
	private final String carName;
	@Id
	private Long id;

	public Car(String carName) {
		this.carName = carName;
	}
}
