package com.tdd.backend.mypage.data;

import java.util.List;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.post.model.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultInfo {
	private final Long id;
	private final String carName;
	private final String rideOption;
	private final String requirement;
	private final Location location;
	private final List<OptionDto> options;

	@Builder
	private DefaultInfo(Long id, String carName, String rideOption, String requirement, Location location,
		List<OptionDto> options) {
		this.id = id;
		this.carName = carName;
		this.rideOption = rideOption;
		this.requirement = requirement;
		this.location = location;
		this.options = options;
	}
}
