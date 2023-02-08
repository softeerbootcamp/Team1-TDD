package com.tdd.backend.post.model;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("appointments")
public class Appointment {

	@Id
	private Long id;

	private final LocalDate date;

	private final AppointmentStatus status;

	public Appointment(LocalDate date, AppointmentStatus status) {
		this.date = date;
		this.status = status;
	}
}