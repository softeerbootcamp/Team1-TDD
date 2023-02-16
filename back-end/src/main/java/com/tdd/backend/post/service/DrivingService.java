package com.tdd.backend.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.exception.PostNotFoundException;
import com.tdd.backend.post.data.AppointmentDto;
import com.tdd.backend.post.data.DrivingDto;
import com.tdd.backend.post.data.DrivingResponse;
import com.tdd.backend.post.exception.LocationNotFoundException;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrivingService {

	private final PostRepository postRepository;

	public DrivingResponse getAllDataByPostId(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(PostNotFoundException::new);
		List<Option> options = postRepository.findOptionByPostId(postId);
		List<Appointment> appointments = postRepository.findAppointmentsByPostId(postId);
		Location location = postRepository.findLocationByPostId(postId)
			.orElseThrow(LocationNotFoundException::new);

		List<OptionDto> optionDtos = options.stream()
			.map(Option::toDto)
			.collect(Collectors.toList());
		List<AppointmentDto> appointmentDtos = appointments.stream()
			.map(Appointment::toDto)
			.collect(Collectors.toList());

		return DrivingResponse.builder()
			.post(post.toPostDto())
			.options(optionDtos)
			.appointments(appointmentDtos)
			.location(location.toDto())
			.build();
	}

	public List<DrivingResponse> getDrivingResponseList(DrivingDto drivingDto) {
		List<String> options = drivingDto.getOptionList();
		List<String> dates = drivingDto.getDateList();
		String carName = drivingDto.getCarName();

		List<Long> postIds;

		if (!options.isEmpty() && !dates.isEmpty()) {
			postIds = postRepository.findPostIdsByOptionsAndDatesAndCarName(options, dates, carName, options.size());
		} else if (!options.isEmpty()) {
			postIds = postRepository.findPostIdsByOptionsAndCarName(options, carName, options.size());
		} else if (!dates.isEmpty()) {
			postIds = postRepository.findPostIdsByDatesAndCarName(dates, carName);
		} else {
			postIds = postRepository.findPostIdsByCarName(carName);
		}

		return getDrivingResponses(postIds);

	}

	private List<DrivingResponse> getDrivingResponses(List<Long> postIdList) {
		return postIdList.stream()
			.map(this::getAllDataByPostId)
			.collect(Collectors.toList());
	}

	public void approveAppointment(Long appointmentId, Long testerId) {
		postRepository.updateTesterIdStatusAccept(appointmentId, testerId);
	}
}
