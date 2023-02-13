package com.tdd.backend.user.data;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.post.model.Appointment;

import lombok.Builder;
import lombok.Getter;

@Table("users")
@Getter
public class User {

	@Id
	private Long id;

	private final String email;
	private final String userName;
	private final String phoneNumber;
	private final String userPassword;
	private final LocalDate createdAt;

	@Column("tester_id")
	private Appointment appointment;

	@Builder
	private User(String email, String userName, String phoneNumber, String userPassword, LocalDate createdAt) {
		this.email = email;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.userPassword = userPassword;
		this.createdAt = createdAt;
	}

	public static User createUser(UserCreate userCreate, String encryptPwd, LocalDate createdAt) {
		return User.builder()
			.email(userCreate.getEmail())
			.userPassword(encryptPwd)
			.userName(userCreate.getUserName())
			.phoneNumber(userCreate.getPhoneNumber())
			.createdAt(createdAt)
			.build();
	}

	public Long getId() {
		return id;
	}
}
