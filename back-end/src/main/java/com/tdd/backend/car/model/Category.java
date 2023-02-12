package com.tdd.backend.car.model;

import java.util.Arrays;

import com.tdd.backend.car.exception.CategoryNotFoundException;

import lombok.Getter;

@Getter
public enum Category {
	ENGINE("엔진"),
	DRIVING_SYSTEM("구동방식"),
	CAR_MODEL("모델"),
	CAR_BODY_TYPE("바디타입"),
	TRANSMISSION("변속기"),
	CAR_OPTION("옵션");

	private final String name;

	Category(String name) {
		this.name = name;
	}

	public static Category getCategory(String category) {
		return Arrays.stream(Category.values())
			.filter(c -> c.getName().equals(category))
			.findFirst()
			.orElseThrow(CategoryNotFoundException::new);
	}
}
