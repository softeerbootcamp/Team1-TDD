package com.tdd.backend.mypage.exception;

public class PostNotFoundException extends RuntimeException {
	private static final String MESSAGE = "db에 post가 없습니다.";

	public PostNotFoundException() {
		super(MESSAGE);
	}
}
