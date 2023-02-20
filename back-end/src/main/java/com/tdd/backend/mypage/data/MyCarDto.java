package com.tdd.backend.mypage.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.model.MyCar;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyCarDto {
	@NotNull
	private final Long userId;
	@NotNull
	private final Long carId;
	private final List<OptionDto> options;

	@Builder
	private MyCarDto(Long userId, Long carId, List<OptionDto> options) {
		this.userId = userId;
		this.carId = carId;
		this.options = options;
	}

	public MyCar toEntity() {
		return new MyCar(userId, carId, options.stream().map(OptionDto::toEntity).collect(Collectors.toSet()));
	}
}
