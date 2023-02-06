package com.tdd.backend.user.data;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserCreate {
	@NotBlank(message = "email 값은 필수입니다.")
	private final String email;

	@NotBlank(message = "userPassword 값은 필수입니다.")
	private final String userPassword;

	@NotBlank(message = "userName 값은 필수입니다.")
	private final String userName;

	@NotBlank(message = "phoneNumber 값은 필수입니다.")
	private final String phoneNumber;
}
