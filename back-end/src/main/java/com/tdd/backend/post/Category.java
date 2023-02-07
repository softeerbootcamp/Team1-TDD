package com.tdd.backend.post;

import lombok.Getter;

@Getter
public enum Category {
	SAFETY_OR_PERFORMANCE("안전/성능"),
	INTELLIGENT_TECHNOLOGY("지능형 안전기술"),
	INTERIOR_OR_EXTERIOR("내장/외장"),
	SEAT("시트"),
	CONVENIENCE("편의"),
	MULTIMEDIA("멀티미디어");

	private final String name;

	Category(String name) {
		this.name = name;
	}

}
