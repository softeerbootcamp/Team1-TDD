package com.tdd.backend.post;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
public class Post {

	@Id
	private Long id;

	private final Long userId;

	private final boolean rideWith;

	private final DriveCareer driveCareer;

	private final String requirement;

	@MappedCollection(idColumn = "post_id")
	private final Set<Option> optionSet = new HashSet<>();

	public void addOption(Option option) {
		optionSet.add(option);
	}

	public Post(Long userId, boolean rideWith, DriveCareer driveCareer, String requirement, Set<Option> optionSet) {
		this.userId = userId;
		this.rideWith = rideWith;
		this.driveCareer = driveCareer;
		this.requirement = requirement;

		for (Option option : optionSet) {
			this.addOption(option);
		}
	}
}
