package com.tdd.backend.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {

	@Id
	private Long id;

	@Column("user_name")
	private final String userName;

	@Column("phone_number")
	private final String phoneNumber;

	@Column("user_password")
	private final String userPassword;

	public User(String userName, String phoneNumber, String userPassword) {
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.userPassword = userPassword;
	}
}
