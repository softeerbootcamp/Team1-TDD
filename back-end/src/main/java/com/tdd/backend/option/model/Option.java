package com.tdd.backend.option.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("entire_options")
@Getter
public class Option {
	@Id
	private Long id;
	private final String optionName;
	private final String categoryName;

	public Option(String optionName, String categoryName) {
		this.optionName = optionName;
		this.categoryName = categoryName;
	}
}
