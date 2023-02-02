package com.tdd.backend.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("options")
public class Option {

	@Id
	private Long id;

	private final String name;

	private final Category category;

	public Option(String name, Category category) {
		this.name = name;
		this.category = category;
	}
}
