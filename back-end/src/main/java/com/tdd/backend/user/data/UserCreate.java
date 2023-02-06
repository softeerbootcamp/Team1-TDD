package com.tdd.backend.user.data;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserCreate {
	@NotBlank
	private final String email;
	@NotBlank
	private final String userPassword;
	@NotBlank
	private final String userName;
	@NotBlank
	private final String phoneNumber;
}
