package com.tdd.backend.mypage.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.post.model.Option;

import lombok.Getter;

@Getter
@Table("mycars")
public class MyCar {
	@MappedCollection(idColumn = "mycar_id")
	private final Set<Option> optionSet = new HashSet<>();
	@Id
	private Long id;
	private final Long userId;
	private final Long carId;

	public MyCar(Long userId, Long carId, Set<Option> options) {
		this.userId = userId;
		this.carId = carId;
		options.forEach(this::addOption);
	}

	private void addOption(Option option) {
		optionSet.add(option);
	}
}
