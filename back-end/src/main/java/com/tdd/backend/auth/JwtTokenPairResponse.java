package com.tdd.backend.auth;

import lombok.Builder;
import lombok.Getter;

@Getter //https://stackoverflow.com/questions/28466207/could-not-find-acceptable-representation-using-spring-boot-starter-web
public class JwtTokenPairResponse {
	private final String accessToken;
	private final String refreshToken;

	@Builder
	private JwtTokenPairResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
