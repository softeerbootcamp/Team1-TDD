package com.tdd.backend.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {

	@Id
	private Long id;

	private final String userName;

	private final String phoneNumber;

	private final String userPassword;

	public User(String userName, String phoneNumber, String userPassword) {
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.userPassword = userPassword;
	}

	public Long getId() {
		return id;
	}
}
