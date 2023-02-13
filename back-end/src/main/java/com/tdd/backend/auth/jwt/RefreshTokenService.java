package com.tdd.backend.auth.jwt;

import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private static final String REFRESH_TOKEN_PREFIX = "refreshToken:";

	private final RedisTemplate<String, String> redisTemplate;

	public void save(Long userId, String refreshToken) {
		redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + userId, refreshToken);
	}

	public boolean isValidateUserId(Long userId) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(REFRESH_TOKEN_PREFIX + userId));
	}

	public void deleteCache(String refreshToken) {
		redisTemplate.delete(REFRESH_TOKEN_PREFIX + refreshToken);
	}

	public void clean() {
		redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(REFRESH_TOKEN_PREFIX + "*")));
	}
}
