package com.tdd.backend.post.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.AppointmentStatus;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SharingDto {
	@NotNull(message = "post 정보는 필수입니다!")
	private final PostDto post;
	@NotNull(message = "location은 필수입니다!")
	private final LocationDto location;
	@NotNull(message = "option은 필수입니다!")
	private final List<OptionDto> options;
	@NotNull(message = "date은 필수입니다!")
	private final List<String> dates;

	@Builder
	private SharingDto(PostDto post, LocationDto location, List<OptionDto> options, List<String> dates) {
		this.post = post;
		this.location = location;
		this.options = options;
		this.dates = dates;
	}

	public Post toEntity(Long userId) {
		Set<Option> optionSet = options.stream().map(OptionDto::toEntity).collect(Collectors.toSet());
		Set<Appointment> appointmentSet = dates.stream()
			.map(date -> new Appointment(LocalDate.parse(date), AppointmentStatus.PENDING))
			.collect(Collectors.toSet());

		return new Post(userId, RideOption.valueOf(post.getRideOption().toUpperCase()), post.getCarName(),
			post.getRequirement(), optionSet, location.toEntity(), appointmentSet);
	}
}
