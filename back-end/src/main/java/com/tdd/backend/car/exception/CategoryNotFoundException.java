package com.tdd.backend.car.exception;

public class CategoryNotFoundException extends RuntimeException {
	private static final String MESSAGE = "존재하지 않는 카테고리 입니다.";

	public CategoryNotFoundException() {
		super(MESSAGE);
	}
}
