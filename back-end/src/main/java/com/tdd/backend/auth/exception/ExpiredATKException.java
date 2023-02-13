package com.tdd.backend.auth.exception;

public class ExpiredATKException extends RuntimeException {
	private static final String MESSAGE = "토큰이 만료되었습니다. 재발급해주세요.";

	public ExpiredATKException() {
		super(MESSAGE);
	}
}
