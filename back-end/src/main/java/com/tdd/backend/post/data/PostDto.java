package com.tdd.backend.post.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDto {
	@NotBlank(message = "car name은 필수입니다!")
	private final String carName;
	@NotBlank(message = "ride option은 필수입니다!")
	private final String rideOption;
	@NotNull(message = "user id는 필수입니다!")
	private final Long userId;
	private final String requirement;

	@Builder
	private PostDto(String carName, String rideOption, Long userId, String requirement) {
		this.carName = carName;
		this.rideOption = rideOption;
		this.userId = userId;
		this.requirement = requirement;
	}
}
