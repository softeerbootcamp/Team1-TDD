package com.tdd.backend.user.data;

import java.util.UUID;

import com.tdd.backend.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Session {
	private final String accessToken;

	private final User user;
	@Builder
	private Session(User user) {
		this.accessToken = UUID.randomUUID().toString();
		this.user = user;
	}
}
