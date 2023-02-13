package com.tdd.backend.mypage.data;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyPageResponse {
	private final UserInfo user;
	private final List<DrivingInfo> driving;
	private final List<SharingInfo> sharing;

	@Builder
	private MyPageResponse(UserInfo user, List<DrivingInfo> driving, List<SharingInfo> sharing) {
		this.user = user;
		this.driving = driving;
		this.sharing = sharing;
	}
}
