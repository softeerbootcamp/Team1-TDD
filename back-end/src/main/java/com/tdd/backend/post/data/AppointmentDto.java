package com.tdd.backend.post.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AppointmentDto {
	private final Long id;
	private final String date;
	private final String status;

	@Builder
	public AppointmentDto(Long id, String date, String status) {
		this.id = id;
		this.date = date;
		this.status = status;
	}
}
