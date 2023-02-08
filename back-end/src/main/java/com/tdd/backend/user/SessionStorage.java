package com.tdd.backend.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tdd.backend.user.data.UserSession;
import com.tdd.backend.user.exception.UnauthorizedException;

/**
 * 세션 디비 역할을 위한 임시적인 세션스토리지 (인메모리)
 * 수정 반드시 필요
 */
public class SessionStorage {
	private static final Map<String, UserSession> sessionMap = new ConcurrentHashMap<>();

	public static void addSession(UserSession userSession) {
		sessionMap.put(userSession.getAccessToken(), userSession);
	}

	public static int getCount() {
		return sessionMap.size();
	}

	public static boolean isSession(String key) {
		return sessionMap.containsKey(key);
	}

	public static UserSession getSession(String key) {
		if (isSession(key)) {
			return sessionMap.get(key);
		}
		throw new UnauthorizedException();
	}

	public static void clean() {
		sessionMap.clear();
	}
}