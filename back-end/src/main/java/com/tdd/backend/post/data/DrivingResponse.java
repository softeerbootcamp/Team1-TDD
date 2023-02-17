package com.tdd.backend.post.data;

import java.util.List;

import com.tdd.backend.car.data.OptionDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DrivingResponse {
	private final PostDto post;
	private final LocationDto location;
	private final List<OptionDto> options;
	private final List<AppointmentDto> appointments;
	private final String imageUrl;
	@Builder
	private DrivingResponse(PostDto post, LocationDto location, List<OptionDto> options,
		List<AppointmentDto> appointments, String imageUrl) {
		this.post = post;
		this.location = location;
		this.options = options;
		this.appointments = appointments;
		this.imageUrl = imageUrl;
	}
}
