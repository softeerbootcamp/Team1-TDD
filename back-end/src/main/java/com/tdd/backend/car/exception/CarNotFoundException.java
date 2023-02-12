package com.tdd.backend.car.exception;

public class CarNotFoundException extends RuntimeException {
	private static final String MESSAGE = "인증되지 않은 접근입니다.";

	public CarNotFoundException() {
		super(MESSAGE);
	}
}
