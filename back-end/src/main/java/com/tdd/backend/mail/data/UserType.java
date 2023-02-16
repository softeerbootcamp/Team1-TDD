package com.tdd.backend.mail.data;

import lombok.Getter;

@Getter
public enum UserType {
	OWNER("시승자", "등록하신 차량 공유가 성사되었습니다."),
	TESTER("공유자", "차량 시승 신청이 완료되었습니다.");
	private final String opponentType;
	private final String subject;

	UserType(String opponentType, String subject) {
		this.opponentType = opponentType;
		this.subject = subject;
	}
}
