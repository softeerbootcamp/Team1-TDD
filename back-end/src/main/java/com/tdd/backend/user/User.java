package com.tdd.backend.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {

	@Id
	private Long id;

	private String userName;
	private String phoneNumber;
	private String userPassword;

}
