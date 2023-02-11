package com.tdd.backend.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * key : email
 * value : refreshToken
 */
public class RefreshTokenStorage {

	private static final Map<String, String> refreshTokenMap = new HashMap<>();

	private RefreshTokenStorage() {
	}

	public static void save(String email, String refreshToken) {
		refreshTokenMap.put(email, refreshToken);
	}

	public static boolean isValidateEmail(String email) {
		return refreshTokenMap.containsKey(email);
	}
}
