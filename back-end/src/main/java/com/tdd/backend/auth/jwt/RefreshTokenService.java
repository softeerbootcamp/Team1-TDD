package com.tdd.backend.auth.jwt;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tdd.backend.auth.data.RefreshToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	@Value("${app.jwt.refreshExpirationInMs}")
	private int jwtRefreshExpirationInMs;

	private final RedisTemplate<String, RefreshToken> redisTemplate;

	public void saveRefreshToken(Long userId, String refreshToken) {
		RefreshToken refreshTokenObject = RefreshToken.builder()
			.userId(userId)
			.refreshToken(refreshToken)
			.build();
		redisTemplate.opsForValue().set(String.valueOf(userId), refreshTokenObject);

		// Set an expiration time for the token
		redisTemplate.expire(String.valueOf(userId), jwtRefreshExpirationInMs, TimeUnit.MILLISECONDS);
	}

	public RefreshToken getRefreshToken(Long userId) {
		return redisTemplate.opsForValue().get(String.valueOf(userId));
	}

	public boolean isRefreshTokenExists(Long userId) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(String.valueOf(userId)));
	}

	public void deleteRefreshToken(Long userId) {
		redisTemplate.delete(String.valueOf(userId));
	}

	public void deleteAll() {
		redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys("*")));
	}

	@Scheduled(fixedDelay = 1800000) // Run every 30 minutes
	public void deleteExpiredRefreshTokens() {
		Set<String> userIds = redisTemplate.keys("*");
		for (String userId : userIds) {
			if (!redisTemplate.hasKey(userId)) {
				continue;
			}
			if (redisTemplate.getExpire(userId) < 0) {
				redisTemplate.delete(userId);
			}
		}
	}
}
