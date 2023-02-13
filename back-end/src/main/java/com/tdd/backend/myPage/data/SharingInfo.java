package com.tdd.backend.myPage.data;

import java.util.List;

import com.tdd.backend.post.data.AppointmentDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SharingInfo {
	private final DefaultInfo post;
	private final List<AppointmentDto> appointments;
	@Builder
	public SharingInfo(DefaultInfo post, List<AppointmentDto> appointments) {
		this.post = post;
		this.appointments = appointments;
	}
}
