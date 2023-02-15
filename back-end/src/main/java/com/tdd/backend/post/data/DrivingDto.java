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

	@Builder
	private DrivingDto(String carName, List<String> optionList, List<String> dateList) {
		this.carName = carName;
		this.optionList = optionList;
		this.dateList = dateList;
	}
}
