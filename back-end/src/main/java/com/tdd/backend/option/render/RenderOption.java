package com.tdd.backend.option.render;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("entire_options")
@Getter
public class RenderOption {
	@Id
	private Long id;
	private final String optionName;
	private final String category;

	public RenderOption(String optionName, String category) {
		this.optionName = optionName;
		this.category = category;
	}
}
