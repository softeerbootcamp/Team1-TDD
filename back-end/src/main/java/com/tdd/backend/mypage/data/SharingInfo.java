package com.tdd.backend.mypage.data;

import java.util.List;

import com.tdd.backend.post.data.AppointmentDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SharingInfo {
	private final DefaultInfo post;
	private final List<AppointmentDto> appointments;

	@Builder
	private SharingInfo(DefaultInfo post, List<AppointmentDto> appointments) {
		this.post = post;
		this.appointments = appointments;
	}
}
