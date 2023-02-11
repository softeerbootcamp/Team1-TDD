package com.tdd.backend.post.data;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AppointmentDto {
	private final Date date;
	private final Long testerId;
	private final String status;

	@Builder
	private AppointmentDto(Date date, Long testerId, String status) {
		this.date = date;
		this.testerId = testerId;
		this.status = status;
	}

}
