package com.tdd.backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private final String code;
	private final String errorMessage;
	private final Map<String, String> validation = new HashMap<>();
	private final LocalDateTime timestamp = LocalDateTime.now();

	public void addValidation(String field, String defaultMessage) {
		validation.put(field, defaultMessage);
	}
}
