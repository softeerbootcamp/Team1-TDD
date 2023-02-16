package com.tdd.backend.mail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailMessage {
	private String to;

	private String subject;

	private String message;
}
