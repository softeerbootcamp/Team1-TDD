package com.tdd.backend.post.data;

import com.tdd.backend.post.Location;

import lombok.Getter;

@Getter
public class LocationDto {
	private String latitude;		//위도
	private String longitude;		//경도

	public LocationDto(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Location toEntity(){
		return new Location(longitude, latitude);
	}
}
