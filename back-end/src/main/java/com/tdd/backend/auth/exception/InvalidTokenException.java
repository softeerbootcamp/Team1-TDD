package com.tdd.backend.auth.exception;

public class InvalidTokenException extends RuntimeException {

	private static final String MESSAGE = "부적절한 토큰입니다.";

	public InvalidTokenException() {
		super(MESSAGE);
	}
}
