package com.tdd.backend.post.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.car.model.Category;

@Table("options")
public class Option {

	private final String name;
	private final Category category;
	@Id
	private Long id;

	public Option(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public OptionDto toDto() {
		return OptionDto.builder()
			.name(name)
			.category(category.getName())
			.build();
	}
}
