package com.tdd.backend.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * key : user_id
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

	public static void deleteCache(String refreshToken) {
		refreshTokenMap.values().remove(refreshToken);
	}

	public static void clean() {
		refreshTokenMap.clear();
	}
}
