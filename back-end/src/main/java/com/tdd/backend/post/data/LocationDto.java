package com.tdd.backend.post.data;

import javax.validation.constraints.NotNull;

import com.tdd.backend.post.model.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LocationDto {
	@NotNull(message = "latitude는 필수입니다.")
	private final String latitude;        //위도
	@NotNull(message = "longitude는 필수입니다.")
	private final String longitude;        //경도

	@Builder
	private LocationDto(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location toEntity() {
		return new Location(longitude, latitude);
	}
}
