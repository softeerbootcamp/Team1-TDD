package com.tdd.backend.auth;

import java.util.HashMap;
import java.util.Map;

public class RefreshTokenStorage {

	private static final Map<String, String> refreshTokenMap = new HashMap<>();

	private RefreshTokenStorage() {
	}

	public static void save(String refreshToken, String email) {
		refreshTokenMap.put(refreshToken, email);
	}
}
