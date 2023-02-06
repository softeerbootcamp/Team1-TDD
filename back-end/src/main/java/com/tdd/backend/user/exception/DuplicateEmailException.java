package com.tdd.backend.user.exception;

public class DuplicateEmailException extends RuntimeException {
	private static final String MESSAGE = "해당하는 유저가 없습니다.";

	public DuplicateEmailException() {
		super(MESSAGE);
	}
}
