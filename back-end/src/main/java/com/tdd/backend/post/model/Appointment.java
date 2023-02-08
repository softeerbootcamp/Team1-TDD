package com.tdd.backend.post.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("appointments")
public class Appointment {

	@Id
	private Long id;

	private final String date;

	private final AppointmentStatus status;

	public Appointment(String date, AppointmentStatus status) {
		this.date = date;
		this.status = status;
	}
}
