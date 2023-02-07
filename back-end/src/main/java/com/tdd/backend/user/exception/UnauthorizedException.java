package com.tdd.backend.user.exception;

public class UnauthorizedException extends RuntimeException {
	private static final String MESSAGE = "인증되지 않은 접근입니다.";

	public UnauthorizedException() {
		super(MESSAGE);
	}
}
