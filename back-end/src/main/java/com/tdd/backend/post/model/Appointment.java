package com.tdd.backend.post.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.post.data.AppointmentDto;

@Table("appointments")
public class Appointment {

	private final LocalDate date;
	private final AppointmentStatus status;
	@Id
	private Long id;

	public Appointment(LocalDate date, AppointmentStatus status) {
		this.date = date;
		this.status = status;
	}

	public AppointmentDto toDto() {
		return AppointmentDto.builder()
			.id(id)
			.date(date.toString())
			.status(status.toString())
			.build();
	}
}
