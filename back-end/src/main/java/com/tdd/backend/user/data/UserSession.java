package com.tdd.backend.user.data;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSession {
	private final String accessToken;

	private final Long userId;

	@Builder
	private UserSession(Long userId) {
		this.accessToken = UUID.randomUUID().toString();
		this.userId = userId;
	}
}
