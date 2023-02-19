package com.tdd.backend.mypage.data;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.tdd.backend.car.data.OptionDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyCarCreate {
	@NotBlank(message = "carName은 필수입니다.")
	private final String carName;
	private final List<OptionDto> optionDtoList;

	@Builder
	private MyCarCreate(String carName, List<OptionDto> optionDtoList) {
		this.carName = carName;
		this.optionDtoList = optionDtoList;
	}
}
