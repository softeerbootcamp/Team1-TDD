package com.tdd.backend.post;

import lombok.Getter;

@Getter
public enum Category {
	SAFETY("안전/성능"),
	DISPLAY("내장/외장"),
	COLOR("색상"),
	ENGINE("엔진");

	private final String name;

	Category(String name) {
		this.name = name;
	}

}
