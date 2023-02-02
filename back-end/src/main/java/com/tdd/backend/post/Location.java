package com.tdd.backend.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("locations")
public class Location {

	@Id
	private Long id;

	private final String locationX;
	private final String locationY;

	public Location(String locationX, String locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
	}
}
