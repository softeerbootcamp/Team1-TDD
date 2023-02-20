package com.tdd.backend.mypage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.car.repository.CarRepository;
import com.tdd.backend.mypage.data.DefaultInfo;
import com.tdd.backend.mypage.data.DrivingInfo;
import com.tdd.backend.mypage.data.MyPageResponse;
import com.tdd.backend.mypage.data.SharingInfo;
import com.tdd.backend.mypage.data.UserInfo;
import com.tdd.backend.mypage.repository.MyCarRepository;
import com.tdd.backend.post.data.AppointmentDto;
import com.tdd.backend.post.data.LocationDto;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.repository.PostRepository;
import com.tdd.backend.user.exception.UserNotFoundException;
import com.tdd.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CarRepository carRepository;
	private final MyCarRepository myCarRepository;

	public MyPageResponse getMyPageInfo(Long userId) {
		List<DrivingInfo> drivingInfoList = getDrivingInfoList(userId);
		List<SharingInfo> sharingInfoList = getSharingInfoList(userId);
		UserInfo userInfo = getUserInfo(userId, drivingInfoList, sharingInfoList);
		return MyPageResponse.builder()
			.user(userInfo)
			.sharing(sharingInfoList)
			.driving(drivingInfoList)
			.build();
	}

	private UserInfo getUserInfo(
		Long userId,
		List<DrivingInfo> drivingInfoList,
		List<SharingInfo> sharingInfoList
	) {
		return userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new)
			.toUserInfo(sharingInfoList.size(), drivingInfoList.size());
	}

	private List<SharingInfo> getSharingInfoList(Long userId) {
		return getDefaultInfoList(userId).stream()
			.map(defaultInfo -> SharingInfo.builder()
				.post(defaultInfo)
				.appointments(getAppointmentListByPostId(defaultInfo.getId()))
			.build()).collect(Collectors.toList());
	}

	private List<DefaultInfo> getDefaultInfoList(Long userId) {
		return getPostIdListByUserId(userId).stream()
			.map(postId -> getDefaultInfo(postId, getOptionListByUserId(userId)))
			.collect(Collectors.toList());
	}

	private String getImageUrl(String carName) {
		return carRepository.findImageUrlByName(carName).orElse("");
	}

	private List<DrivingInfo> getDrivingInfoList(Long userId) {
		return getPostIdListByTesterId(userId).stream()
			.map(postId -> DrivingInfo.builder()
				.post(getDefaultInfo(postId, getOptionListByUserId(userId)))
				.date(getDate(userId, postId))
				.build())
			.collect(Collectors.toList());
	}

	private List<Long> getPostIdListByTesterId(Long userId) {
		return postRepository.findPostIdByTesterId(userId);
	}

	private List<Long> getPostIdListByUserId(Long userId) {
		return postRepository.findPostIdsByUserId(userId);
	}

	private String getDate(Long userId, Long postId) {
		return postRepository.findDateByPostIdAndUserId(postId, userId).orElse("");
	}

	private DefaultInfo getDefaultInfo(Long postId, List<OptionDto> optionDtoList) {
		String carName = carRepository.findCarNameByPostId(postId).orElseThrow(RuntimeException::new);
		return postRepository.findById(postId)
			.map(post -> post.toDefaultInfo(
				optionDtoList,
				carName,
				getLocation(postId),
				getImageUrl(carName)))
			.orElse(DefaultInfo.builder().build());
	}

	private LocationDto getLocation(Long postId) {
		return postRepository.findLocationByPostId(postId)
			.map(Location::toDto)
			.orElse(LocationDto.builder().build());
	}

	private List<OptionDto> getOptionListByUserId(Long userId) {
		Long carId = myCarRepository.findCarIdByUserId(userId).orElseThrow(RuntimeException::new);
		return myCarRepository.findOptionsByUserIdAndCarId(userId, carId)
			.stream()
			.map(Option::toDto)
			.collect(Collectors.toList());
	}

	private List<AppointmentDto> getAppointmentListByPostId(Long postId) {
		return postRepository.findAppointmentsByPostId(postId)
			.stream()
			.map(Appointment::toDto)
			.collect(Collectors.toList());
	}
}
