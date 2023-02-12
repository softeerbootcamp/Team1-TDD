package com.tdd.backend.auth.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenPairResponse {
	private final String accessToken;
	private final String refreshToken;

	@Builder
	private JwtTokenPairResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
