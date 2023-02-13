package com.tdd.backend.mypage.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {
	private final String email;
	private final String userName;
	private final String phoneNumber;
	private final String createdAt;
	private final int drivingCount;
	private final int sharingCount;

	@Builder
	private UserInfo(String email, String userName, String phoneNumber, String createdAt, int sharingCount,
		int drivingCount) {
		this.email = email;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.sharingCount = sharingCount;
		this.drivingCount = drivingCount;
	}
}
