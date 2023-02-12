package com.tdd.backend.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * key : email
 * value : refreshToken
 */
public class RefreshTokenStorage {

	private static final Map<Long, String> refreshTokenMap = new HashMap<>();

	private RefreshTokenStorage() {
	}

	public static void save(Long id, String refreshToken) {
		refreshTokenMap.put(id, refreshToken);
	}

	public static boolean isValidateUserId(Long id) {
		return refreshTokenMap.containsKey(id);
	}

	public static void deleteCache(Long id) {
		refreshTokenMap.remove(id);
	}
}
