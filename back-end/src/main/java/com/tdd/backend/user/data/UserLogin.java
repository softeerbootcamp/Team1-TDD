package com.tdd.backend.user.data;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLogin {
	@NotBlank
	private final String email;
	@NotBlank
	private final String userPassword;

	@Builder
	private UserLogin(String email, String userPassword) {
		this.email = email;
		this.userPassword = userPassword;
	}
}
