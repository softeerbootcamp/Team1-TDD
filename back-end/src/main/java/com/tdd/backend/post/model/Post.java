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
import com.tdd.backend.post.data.LocationDto;
import com.tdd.backend.post.data.PostDto;

import lombok.Getter;

@Getter
@Table("posts")
public class Post {
	private final RideOption rideOption;
	private final String requirement;

	@MappedCollection(idColumn = "post_id")
	private final Set<Appointment> appointmentSet = new HashSet<>();
	@Column("post_id")
	private final Location location;
	private final Long mycarId;
	@Id
	private Long id;

	public Post(
		RideOption rideOption,
		String requirement,
		Location location,
		Set<Appointment> appointmentSet,
		Long mycarId
	) {
		this.rideOption = rideOption;
		this.requirement = requirement;
		this.location = location;
		this.mycarId = mycarId;

		for (Appointment appointment : appointmentSet) {
			this.addAppointment(appointment);
		}

	}

	public void addAppointment(Appointment appointment) {
		appointmentSet.add(appointment);
	}

	public PostDto toPostDto(String carName) {
		return PostDto.builder()
			.id(id)
			.carName(carName)
			.requirement(requirement)
			.rideOption(rideOption.toString())
			.build();
	}

	public DefaultInfo toDefaultInfo(List<OptionDto> options, String carName, LocationDto location, String imageUrl) {
		return DefaultInfo.builder()
			.id(id)
			.carName(carName)
			.rideOption(rideOption.toString())
			.requirement(requirement)
			.options(options)
			.location(location)
			.imageUrl(imageUrl)
			.build();
	}
}
