package com.tdd.backend.user.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserToken {

	private final Long id;

	@Builder
	private UserToken(Long id) {
		this.id = id;
	}
}
