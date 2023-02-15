package com.tdd.backend.post.exception;

public class LocationNotFoundException extends RuntimeException {
	private static final String MESSAGE = "location db에 해당 postid를 가진 래코드가 없습니다.";

	public LocationNotFoundException() {
		super(MESSAGE);
	}
}
