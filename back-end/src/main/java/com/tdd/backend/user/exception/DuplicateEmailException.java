package com.tdd.backend.user.exception;

public class DuplicateEmailException extends RuntimeException {
	private static final String MESSAGE = "중복된 이메일입니다.";

	public DuplicateEmailException() {
		super(MESSAGE);
	}
}
