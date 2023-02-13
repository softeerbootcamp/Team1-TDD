package com.tdd.backend.car.exception;

public class CarNotFoundException extends RuntimeException {
	private static final String MESSAGE = "존재하지 않는 차 입니다.";

	public CarNotFoundException() {
		super(MESSAGE);
	}
}
