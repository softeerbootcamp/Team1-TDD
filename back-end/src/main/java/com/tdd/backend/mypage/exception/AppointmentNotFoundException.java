package com.tdd.backend.mypage.exception;

public class AppointmentNotFoundException extends RuntimeException {
	private static final String MESSAGE = "appointment db에 해당 userid와 postid를 가진 래코드가 없습니다.";

	public AppointmentNotFoundException() {
		super(MESSAGE);
	}
}
