package com.tdd.backend.post.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.option.Category;

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
