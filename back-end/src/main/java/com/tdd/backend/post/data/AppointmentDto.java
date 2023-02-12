package com.tdd.backend.post.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AppointmentDto {
	private final Long id;
	private final String date;

	@Builder
	public AppointmentDto(Long id, String date) {
		this.id = id;
		this.date = date;
	}
}
