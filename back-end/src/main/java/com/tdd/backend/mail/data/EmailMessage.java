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
	private static final String image = "<a target=\"_blank\" href=\"https://letstdd.site\">"
		+ "<img style=\"max-width:30%; height:auto;\" src =\"https://url.kr/mqafdo\"/></a>";

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
		sb.append(image);
		sb.append("<p>" + userStr + " 성함: " + name + "</p>");
		sb.append("<p>" + userStr + " 번호: " + phoneNumber + "</p>");
		sb.append("<p>" + userStr + " 메일: " + email + "</p>");
		sb.append("-------------------------------</p>");
		sb.append("<p>" + "차종: " + carName + "</p>");
		sb.append("<p>" + "시승 날짜: " + date + "</p>");
		sb.append("<p>" + "세부 사항: " + requirement + "</p>");
		return sb.toString();
	}

}
