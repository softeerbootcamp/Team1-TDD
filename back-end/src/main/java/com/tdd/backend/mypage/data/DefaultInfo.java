package com.tdd.backend.mypage.data;

import java.util.List;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.post.data.LocationDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultInfo {
	private final Long id;
	private final String carName;
	private final String rideOption;
	private final String requirement;
	private final LocationDto location;
	private final List<OptionDto> options;
	private final String imageUrl;

	@Builder
	private DefaultInfo(Long id, String carName, String rideOption, String requirement, LocationDto location,
		List<OptionDto> options, String imageUrl) {
		this.id = id;
		this.carName = carName;
		this.rideOption = rideOption;
		this.requirement = requirement;
		this.location = location;
		this.options = options;
		this.imageUrl = imageUrl;
	}
}
