package com.tdd.backend.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.post.PostRepository;
import com.tdd.backend.post.data.AppointmentDto;
import com.tdd.backend.post.data.DrivingResponse;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrivingService {

	private final PostRepository postRepository;

	public DrivingResponse getAllDataByPostId(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		List<Option> options = postRepository.findOptionByPostId(postId);
		List<Appointment> appointments = postRepository.findAppointmentsByPostId(postId);
		Optional<Location> location = postRepository.findLocationByPostId(postId);

		List<OptionDto> optionDtos = options.stream().map(Option::toDto).collect(Collectors.toList());
		List<AppointmentDto> appointmentDtos = appointments.stream().map(Appointment::toDto).collect(Collectors.toList());

		return DrivingResponse.builder()
			.post(post.orElseThrow(RuntimeException::new).toPostDto())
			.options(optionDtos)
			.appointments(appointmentDtos)
			.location(location.orElseThrow(RuntimeException::new).toDto())
			.build();
	}

	public void approveAppointment(Long id, Long testerId) {
		postRepository.updateTesterIdStatusAccept(id, testerId);
	}
}
