package com.tdd.backend.mypage.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DrivingInfo {
	private final DefaultInfo post;
	private final String date;
	@Builder
	private DrivingInfo(DefaultInfo post, String date) {
		this.post = post;
		this.date = date;
	}
}
