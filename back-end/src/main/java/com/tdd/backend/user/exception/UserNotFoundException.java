package com.tdd.backend.user.exception;

public class UserNotFoundException extends RuntimeException {
	private static final String MESSAGE = "해당하는 유저가 없습니다.";

	public UserNotFoundException() {
		super(MESSAGE);
	}
}
