package com.tdd.backend.option.data;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionResponse {
	private final String category;
	List<OptionDto> options;

	@Builder
	public OptionResponse(String category, List<OptionDto> options) {
		this.category = category;
		this.options = options;
	}
}
