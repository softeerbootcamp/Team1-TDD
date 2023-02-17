package com.tdd.backend.mail.exception;

public class EmailNotFoundException extends RuntimeException {
	private static final String MESSAGE = "user id로 email을 찾을 수 없습니다.";

	public EmailNotFoundException() {
		super(MESSAGE);
	}
}
