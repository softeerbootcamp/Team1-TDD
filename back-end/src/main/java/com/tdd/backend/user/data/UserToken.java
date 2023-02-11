package com.tdd.backend.user.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserToken {

	private final String userName;

	@Builder
	private UserToken(String userName) {
		this.userName = userName;
	}
}
