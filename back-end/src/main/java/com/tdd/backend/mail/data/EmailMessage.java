package com.tdd.backend.mail.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailMessage {

	private final String to;
	private final String name;
	private final String phoneNumber;
	private final String email;
	private final String date;
	private final String carName;
	private final String requirement;
	private final String subject;
	private final UserType userType;

	@Builder
	private EmailMessage(String to, String name, String phoneNumber, String email, String date, String carName,
		String requirement, String subject, UserType userType) {
		this.to = to;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.date = date;
		this.carName = carName;
		this.requirement = requirement;
		this.subject = subject;
		this.userType = userType;
	}

	public String getMessage() {
		String userStr = userType.getOpponentType();

		StringBuilder sb = new StringBuilder();
		sb.append(userStr + " 성함: " + name + "\n");
		sb.append(userStr + " 번호: " + phoneNumber + "\n");
		sb.append(userStr + " 메일: " + email + "\n");
		sb.append("-------------------------\n");
		sb.append("차종: " + carName + "\n");
		sb.append("시승 날짜: " + date + "\n");
		sb.append("세부 사항: " + requirement);
		return sb.toString();
	}

}
