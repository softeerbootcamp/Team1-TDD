package com.tdd.backend.post.data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.tdd.backend.option.Category;
import com.tdd.backend.post.Appointment;
import com.tdd.backend.post.AppointmentStatus;
import com.tdd.backend.post.Location;
import com.tdd.backend.post.Option;
import com.tdd.backend.post.Post;
import com.tdd.backend.post.RideOption;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SharingDto {
	@NotBlank(message = "car name is essential!!")
	private final String carName;
	@NotBlank(message = "location is essential!!")
	private String latitude;		//위도
	@NotBlank(message = "location is essential!!")
	private String longitude;		//경도
	@NotBlank(message = "options is essential")
	private final List<Map<String, String>> options;
	@NotBlank(message = "ride Option is essential!!")
	private final String rideOption;
	@NotBlank(message = "user id is essential!!")
	private final Long userId;
	@NotBlank(message = "date is essential!!")
	private final List<String> dates;
	private final String requirement;

	public Post toEntity() {
		//options.stream().
		Set<Option> optionSet = new HashSet<>();
		for(Map<String,String> option : options) {
			optionSet.add(new Option(option.get("name"), Category.getCategory(option.get("category"))));
		}

		Set<Appointment> appointments = new HashSet<>();
		for (String date : dates) {
			appointments.add(new Appointment(date, AppointmentStatus.PENDING));
		}
		Post post = new Post(userId, RideOption.valueOf(rideOption.toUpperCase()), carName, requirement, optionSet, new Location(longitude, latitude), appointments);
		return post;
	}
}
