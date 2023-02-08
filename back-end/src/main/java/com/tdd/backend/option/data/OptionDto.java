package com.tdd.backend.option.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionDto {
	private final String name;
	private final String category;

	@Builder
	public OptionDto(String name, String category) {
		this.name = name;
		this.category = category;
	}
}
