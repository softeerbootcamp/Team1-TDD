package com.tdd.backend.post.data;

import java.util.List;

import com.tdd.backend.option.data.OptionDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DrivingResponse {
	private final PostDto post;
	private final LocationDto location;
	private final List<OptionDto> options;
	private final List<AppointmentDto> appointments;

	@Builder
	private DrivingResponse(PostDto post, LocationDto location, List<OptionDto> options,
		List<AppointmentDto> appointments) {
		this.post = post;
		this.location = location;
		this.options = options;
		this.appointments = appointments;
	}
}
