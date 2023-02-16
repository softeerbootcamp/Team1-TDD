package com.tdd.backend.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Table("entire_options")
@Getter
public class Option {
	private final String optionName;
	private final String categoryName;
	@Id
	private Long id;

	public Option(String optionName, String categoryName) {
		this.optionName = optionName;
		this.categoryName = categoryName;
	}
}
