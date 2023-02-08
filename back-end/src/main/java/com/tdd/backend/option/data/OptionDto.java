package com.tdd.backend.option.data;

import com.tdd.backend.option.Category;
import com.tdd.backend.post.model.Option;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionDto {
	private final String name;
	private final String category;

	@Builder
	public OptionDto(String name, String category) {
		this.name = name;
		this.category = category;
	}
	public Option toEntity() {
		return new Option(name, Category.getCategory(category));
	}
}
