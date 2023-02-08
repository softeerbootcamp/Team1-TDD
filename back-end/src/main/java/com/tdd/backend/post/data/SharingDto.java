package com.tdd.backend.post.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.post.Appointment;
import com.tdd.backend.post.AppointmentStatus;
import com.tdd.backend.post.Option;
import com.tdd.backend.post.Post;
import com.tdd.backend.post.RideOption;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SharingDto {
	@NotBlank(message = "car name is essential!!")
	private final String carName;
	@NotNull(message = "location is essential!")
	private final LocationDto location;
	@NotNull(message = "Option is essential!")
	private final List<OptionDto> options;
	@NotBlank(message = "ride Option is essential!!")
	private final String rideOption;
	@NotNull(message = "user id is essential!")
	private final Long userId;
	@NotNull(message = "date is essential!!")
	private final List<String> dates;
	private final String requirement;
	public Post toEntity() {
		Set<Option> optionSet = new HashSet<>();
		options.forEach(optionDto -> optionSet.add(optionDto.toEntity()));
		Set<Appointment> appointments = new HashSet<>();
		for (String date : dates) {
			appointments.add(new Appointment(date, AppointmentStatus.PENDING));
		}
		return new Post(userId, RideOption.valueOf(rideOption.toUpperCase()), carName, requirement, optionSet, location.toEntity(), appointments);
	}
}
