package com.tdd.backend.post;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("appointments")
public class Appointment {

	@Id
	private Long id;

	private LocalDateTime date;

	private String status;
}
