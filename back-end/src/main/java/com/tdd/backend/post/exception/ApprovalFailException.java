package com.tdd.backend.post.exception;

public class ApprovalFailException extends RuntimeException {
	private static final String MESSAGE = "이미 예약된 정보입니다.";

	public ApprovalFailException() {
		super(MESSAGE);
	}
}
