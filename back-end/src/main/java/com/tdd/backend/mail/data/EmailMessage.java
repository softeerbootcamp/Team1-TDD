package com.tdd.backend.mail.data;

import lombok.Getter;

@Getter
public class EmailMessage {

	@Getter
	public enum UserType {
		OWNER("공유지", "등록하신 차량 공유가 성사되었습니다."),
		TESTER("시승자", "차량 시승 신청이 완료되었습니다.");
		private final String name;
		private final String subject;

		UserType(String name, String subject) {
			this.name = name;
			this.subject = subject;
		}
	}

}
