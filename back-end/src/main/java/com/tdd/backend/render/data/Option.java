package com.tdd.backend.render.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Option {
	private final String name;
	private final String category;

	@Builder
	public Option(String name, String category) {
		this.name = name;
		this.category = category;
	}
}
