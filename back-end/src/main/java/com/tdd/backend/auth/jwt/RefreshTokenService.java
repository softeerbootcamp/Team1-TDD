package com.tdd.backend.auth.jwt;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.RefreshToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private final RedisTemplate<String, RefreshToken> redisTemplate;

	public void saveRefreshToken(Long userId, String refreshToken) {
		RefreshToken refreshTokenObject = RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
		redisTemplate.opsForValue().set(String.valueOf(userId), refreshTokenObject);
	}

	public RefreshToken getRefreshToken(Long userId) {
		return redisTemplate.opsForValue().get(String.valueOf(userId));
	}

	public void deleteRefreshToken(Long userId) {
		redisTemplate.delete(String.valueOf(userId));
	}
}
