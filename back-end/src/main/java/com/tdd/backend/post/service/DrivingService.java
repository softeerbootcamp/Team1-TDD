package com.tdd.backend.post.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.car.exception.CarNotFoundException;
import com.tdd.backend.car.model.Car;
import com.tdd.backend.car.repository.CarRepository;
import com.tdd.backend.mypage.exception.PostNotFoundException;
import com.tdd.backend.mypage.repository.MyCarRepository;
import com.tdd.backend.post.data.AppointmentDto;
import com.tdd.backend.post.data.DrivingDto;
import com.tdd.backend.post.data.DrivingResponse;
import com.tdd.backend.post.data.LocationDto;
import com.tdd.backend.post.exception.ApprovalFailException;
import com.tdd.backend.post.exception.LocationNotFoundException;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.repository.PostRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrivingService implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher eventPublisher;

	private final CarRepository carRepository;
	private final PostRepository postRepository;
	private final MyCarRepository myCarRepository;

	public DrivingResponse getAllDataByPostId(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		Long myCarId = post.getMycarId();
		Long carId = myCarRepository.findCarIdById(myCarId).orElseThrow();
		Car car = carRepository.findById(carId).orElseThrow();
		String imageUrl = car.getCarImageUrl();

		return DrivingResponse.builder()
			.post(post.toPostDto(car.getCarName()))
			.options(getOptionsDto(myCarId))
			.appointments(getAppointmentDtos(postId))
			.location(getLocation(postId))
			.imageUrl(imageUrl)
			.build();
	}

	private List<AppointmentDto> getAppointmentDtos(Long postId) {
		return postRepository.findAppointmentsByPostId(postId).stream()
			.map(Appointment::toDto)
			.collect(Collectors.toList());
	}

	private LocationDto getLocation(Long postId) {
		return postRepository.findLocationByPostId(postId)
			.orElseThrow(LocationNotFoundException::new)
			.toDto();
	}

	private List<OptionDto> getOptionsDto(Long myCarId) {
		return postRepository.findOptionsByMyCarId(myCarId).stream()
			.map(Option::toDto)
			.collect(Collectors.toList());
	}

	public List<DrivingResponse> getDrivingResponseList(DrivingDto drivingDto) {
		List<String> options = drivingDto.getOptionList();
		List<String> dates = drivingDto.getDateList();

		String startLong = drivingDto.getQuadThree().getLongitude();
		String startLat = drivingDto.getQuadThree().getLatitude();
		String endLong = drivingDto.getQuadOne().getLongitude();
		String endLat = drivingDto.getQuadOne().getLatitude();

		Long carId = carRepository.findIdByCarName(drivingDto.getCarName()).orElseThrow(CarNotFoundException::new);
		List<Long> postIds;

		if (!options.isEmpty() && !dates.isEmpty()) {
			postIds = postRepository.findPostIdsByOptionsAndDatesAndCarIdAndLocation(
				options, dates, carId, options.size(), startLong, startLat, endLong, endLat);
		} else if (!options.isEmpty()) {
			postIds = postRepository.findPostIdsByOptionsAndCarIdAndLocation(options, carId, options.size(),
				startLong, startLat, endLong, endLat);
		} else if (!dates.isEmpty()) {
			postIds = postRepository.findPostIdsByDatesAndCarIdLocation(dates, carId,
				startLong, startLat, endLong, endLat);
		} else {
			postIds = postRepository.findPostIdsByCarIdLocation(carId,
				startLong, startLat, endLong, endLat);
		}

		return getDrivingResponses(postIds);

	}

	private List<DrivingResponse> getDrivingResponses(List<Long> postIdList) {
		return postIdList.stream()
			.map(this::getAllDataByPostId)
			.filter(drivingResponse -> !drivingResponse.getAppointments().isEmpty())
			.collect(Collectors.toList());
	}

	public void approveAppointment(Long appointmentId, Long testerId) {
		int success = postRepository.updateTesterIdStatusAccept(appointmentId, testerId);
		log.info("Approval success count: " + success);
		if (success == 0) {
			throw new ApprovalFailException();
		}
		eventPublisher.publishEvent(new AppointmentMailEvent(appointmentId, testerId));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	@Getter
	public static class AppointmentMailEvent {

		private final Long appointmentId;
		private final Long testerId;

		public AppointmentMailEvent(@NotNull Long appointmentId, @NotNull Long testerId) {
			this.appointmentId = appointmentId;
			this.testerId = testerId;
		}
	}
}
