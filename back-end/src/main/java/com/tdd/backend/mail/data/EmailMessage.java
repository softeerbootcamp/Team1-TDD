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
	public EmailMessage(String to, String name, String phoneNumber, String email, String date, String carName,
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
		String userStr = userType.getName();
		return new StringBuilder()
			.append(userStr + " 성함: " + name + "\n")
			.append(userStr + " 번호: " + phoneNumber + "\n")
			.append(userStr + " 메일: " + email + "\n")
			.append("-------------------------\n")
			.append("차종: " + carName + "\n")
			.append("시승 날짜: " + date + "\n")
			.append("세부 사항: " + requirement)
			.toString();
	}

	@Getter
	public enum UserType {
		OWNER("공유자", "등록하신 차량 공유가 성사되었습니다."),
		TESTER("시승자", "차량 시승 신청이 완료되었습니다.");
		private String name;
		private String subject;

		UserType(String name, String subject) {
			this.name = name;
			this.subject = subject;
		}
	}

}
