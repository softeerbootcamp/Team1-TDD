package com.tdd.backend.post.data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.AppointmentStatus;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SharingDto {
	@NotBlank(message = "car name은 필수입니다!")
	private final String carName;
	@NotNull(message = "location은 필수입니다!")
	private final LocationDto location;
	@NotNull(message = "option은 필수입니다!")
	private final List<OptionDto> options;
	@NotBlank(message = "ride option은 필수입니다!")
	private final String rideOption;
	@NotNull(message = "user id는 필수입니다!")
	private final Long userId;
	@NotNull(message = "date은 필수입니다!")
	private final List<String> dates;
	private final String requirement;
	public Post toEntity() {
		Set<Option> optionSet = new HashSet<>();
		options.forEach(optionDto -> optionSet.add(optionDto.toEntity()));
		Set<Appointment> appointments = new HashSet<>();
		for (String date : dates) {
			appointments.add(new Appointment(LocalDate.parse(date), AppointmentStatus.PENDING));
		}
		return new Post(userId, RideOption.valueOf(rideOption.toUpperCase()), carName, requirement, optionSet, location.toEntity(), appointments);
	}
}
