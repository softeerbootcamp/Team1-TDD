package com.tdd.backend.user.data;

import com.tdd.backend.user.User;

import lombok.Getter;

@Getter
public class UserResponse {

	private final String email;
	private final String userPassword;
	private final String userName;
	private final String phoneNumber;
	private final Long id;

	public UserResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.userPassword = user.getUserPassword();
		this.userName = user.getUserName();
		this.phoneNumber = user.getPhoneNumber();

	}
}
