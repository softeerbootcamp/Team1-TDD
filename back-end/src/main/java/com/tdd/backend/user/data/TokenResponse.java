package com.tdd.backend.user.data;

import lombok.Getter;

@Getter
public class TokenResponse {
	private final String accessToken;

	public TokenResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
