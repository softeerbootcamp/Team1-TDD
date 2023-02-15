package com.tdd.backend.post.data;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TesterDto {
	@NotNull(message = "시승자의 아이디는 필수입니다.")
	private final Long testerId;

	//TODO: 해결법 찾습니다..
	public TesterDto() {
		testerId = null;
	}

	@Builder
	private TesterDto(Long testerId) {
		this.testerId = testerId;
	}
}
