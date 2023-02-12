package com.tdd.backend.option.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("entire_options")
@Getter
public class Option {
	private final String optionName;
	private final String category;
	@Id
	private Long id;

	public Option(String optionName, String category) {
		this.optionName = optionName;
		this.category = category;
	}
}
