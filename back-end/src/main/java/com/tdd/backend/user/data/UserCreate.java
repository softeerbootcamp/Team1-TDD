package com.tdd.backend.user.data;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserCreate {
	private final String email;
	private final String userPassword;

	private final String userName;
	private final String phoneNumber;
}
