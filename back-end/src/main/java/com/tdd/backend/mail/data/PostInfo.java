package com.tdd.backend.mail.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostInfo {
	private final String carName;
	private final String requirement;
	private final Long userId;
	private final String date;
	@Builder
	public PostInfo(String carName, String date, String requirement, Long userId) {
		this.carName = carName;
		this.date = date;
		this.requirement = requirement;
		this.userId = userId;
	}
}
