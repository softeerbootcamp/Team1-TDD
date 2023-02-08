package com.tdd.backend.user.data;

import lombok.Getter;

@Getter
public class SessionResponse {
	private final String accessToken;

	public SessionResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
