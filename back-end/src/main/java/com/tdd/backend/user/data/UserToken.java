package com.tdd.backend.user.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserToken {

	private final String email;

	@Builder
	private UserToken(String email) {
		this.email = email;
	}
}
