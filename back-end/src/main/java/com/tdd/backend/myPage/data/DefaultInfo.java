package com.tdd.backend.myPage.data;

import java.util.List;

import com.tdd.backend.car.data.OptionDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultInfo {
	private final Long id;
	private final String carName;
	private final String rideOption;
	private final String requirement;
	private final List<OptionDto> options;

	@Builder
	private DefaultInfo(Long id, String carName, String rideOption, String requirement, List<OptionDto> options) {
		this.id = id;
		this.carName = carName;
		this.rideOption = rideOption;
		this.requirement = requirement;
		this.options = options;
	}
}
