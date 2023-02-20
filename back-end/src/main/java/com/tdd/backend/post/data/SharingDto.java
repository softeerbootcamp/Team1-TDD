package com.tdd.backend.post.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.AppointmentStatus;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SharingDto {
	@NotNull(message = "rideOption은 필수입니다!")
	private final String rideOption;
	private final String requirement;
	@NotNull(message = "myCarId는 필수입니다!")
	private final Long myCarId;
	@NotNull(message = "location은 필수입니다!")
	private final LocationDto location;

	@NotNull(message = "date은 필수입니다!")
	private final List<String> dates;

	@Builder
	private SharingDto(String rideOption, String requirement, Long myCarId, LocationDto location, List<String> dates) {
		this.rideOption = rideOption;
		this.requirement = requirement;
		this.myCarId = myCarId;
		this.location = location;
		this.dates = dates;
	}

	public Post toEntity() {
		Set<Appointment> appointmentSet = dates.stream()
			.map(date -> new Appointment(LocalDate.parse(date), AppointmentStatus.PENDING))
			.collect(Collectors.toSet());

		return new Post(
			RideOption.valueOf(rideOption),
			requirement,
			location.toEntity(),
			appointmentSet,
			myCarId
		);
	}
}
