package com.tdd.backend.render.data;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionResponse {
	private final String category;
	List<Option> options;

	@Builder
	public OptionResponse(String category, List<Option> options) {
		this.category = category;
		this.options = options;
	}
}
