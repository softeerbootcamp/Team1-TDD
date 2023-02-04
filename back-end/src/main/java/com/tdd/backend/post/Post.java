package com.tdd.backend.post;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
public class Post {

	@Id
	private Long id;

	private final Long userId;

	private final RideOption rideOption;

	private final DriveCareer driveCareer;

	private final String carName;

	private final String requirement;

	@MappedCollection(idColumn = "post_id")
	private final Set<Option> optionSet = new HashSet<>();

	public void addOption(Option option) {
		optionSet.add(option);
	}

	@MappedCollection(idColumn = "post_id")
	private final Set<Appointment> appointmentSet = new HashSet<>();

	@MappedCollection(idColumn = "post_id")
	public void addAppointment(Appointment appointment) {
		appointmentSet.add(appointment);
	}

	@Column("post_id")
	private final Location location;

	public Post(Long userId, RideOption rideOption, DriveCareer driveCareer, String carName, String requirement,
		Set<Option> optionSet, Location location, Set<Appointment> appointmentSet) {
		this.userId = userId;
		this.rideOption = rideOption;
		this.driveCareer = driveCareer;
		this.requirement = requirement;
		this.location = location;
		this.carName = carName;

		for (Option option : optionSet) {
			this.addOption(option);
		}

		for (Appointment appointment : appointmentSet) {
			this.addAppointment(appointment);
		}
	}
}
