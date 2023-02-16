package com.tdd.backend.user.data;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.mypage.data.UserInfo;

import lombok.Builder;
import lombok.Getter;

@Table("users")
@Getter
public class User {

	private final String email;
	private final String userName;
	private final String phoneNumber;
	private final String userPassword;
	private final LocalDate createdAt;
	@Id
	private Long id;

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

	public UserInfo toUserInfo(int sharingCount, int drivingCount) {
		return UserInfo.builder()
			.userName(userName)
			.phoneNumber(phoneNumber)
			.email(email)
			.createdAt(createdAt.toString())
			.sharingCount(sharingCount)
			.drivingCount(drivingCount)
			.build();
	}
}
