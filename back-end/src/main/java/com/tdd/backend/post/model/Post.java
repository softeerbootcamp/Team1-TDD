package com.tdd.backend.post.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.data.DefaultInfo;
import com.tdd.backend.post.data.PostDto;

import lombok.Getter;

@Table("posts")
@Getter
public class Post {

	private final Long userId;
	private final RideOption rideOption;
	private final String carName;
	private final String requirement;
	@MappedCollection(idColumn = "post_id")
	private final Set<Option> optionSet = new HashSet<>();
	@MappedCollection(idColumn = "post_id")
	private final Set<Appointment> appointmentSet = new HashSet<>();
	@Column("post_id")
	private final Location location;
	@Id
	private Long id;

	public Post(Long userId, RideOption rideOption, String carName, String requirement,
		Set<Option> optionSet, Location location, Set<Appointment> appointmentSet) {
		this.userId = userId;
		this.rideOption = rideOption;
		this.carName = carName;
		this.requirement = requirement;
		this.location = location;

		for (Option option : optionSet) {
			this.addOption(option);
		}

		for (Appointment appointment : appointmentSet) {
			this.addAppointment(appointment);
		}
	}

	public void addOption(Option option) {
		optionSet.add(option);
	}

	public void addAppointment(Appointment appointment) {
		appointmentSet.add(appointment);
	}

	public PostDto toPostDto() {
		return PostDto.builder()
			.carName(carName)
			.userId(userId)
			.requirement(requirement)
			.rideOption(rideOption.toString())
			.build();
	}

	public DefaultInfo toDefaultInfo(List<OptionDto> options) {
		return DefaultInfo.builder()
			.id(id)
			.carName(carName)
			.rideOption(rideOption.toString())
			.requirement(requirement)
			.options(options)
			.build();
	}
}
