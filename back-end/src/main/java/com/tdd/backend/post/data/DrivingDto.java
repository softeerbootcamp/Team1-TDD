package com.tdd.backend.post.data;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DrivingDto {
	@NotBlank(message = "car name은 필수입니다!")
	private final String carName;
	private final List<String> dateList;
	private final List<String> optionList;

	@NotBlank(message = "바운더리 설정은 필수입니다!")
	private final LocationDto quadThree;
	@NotBlank(message = "바운더리 설정은 필수입니다!")
	private final LocationDto quadOne;

	@Builder
	private DrivingDto(String carName, List<String> optionList, List<String> dateList,
		LocationDto quadThree, LocationDto quadOne) {
		this.carName = carName;
		this.optionList = optionList;
		this.dateList = dateList;
		this.quadThree = quadThree;
		this.quadOne = quadOne;
	}
}
