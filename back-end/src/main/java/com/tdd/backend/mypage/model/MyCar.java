package com.tdd.backend.mypage.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;

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

	@Column("mycar_id")
	private Post post;

	public void setPost(Post post) {
		this.post = post;
	}

	public MyCar(Long userId, Long carId, Set<Option> options) {
		this.userId = userId;
		this.carId = carId;
		options.forEach(this::addOption);
	}

	private void addOption(Option option) {
		optionSet.add(option);
	}
}
