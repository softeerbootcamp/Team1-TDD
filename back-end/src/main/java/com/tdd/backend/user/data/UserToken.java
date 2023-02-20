package com.tdd.backend.user.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserToken {

	private final Long id;
	private final String email;

	@Builder
	private UserToken(Long id, String email) {
		this.id = id;
		this.email = email;
	}
}
