package com.tdd.backend.mypage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.car.model.Car;
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
import com.tdd.backend.post.model.Post;
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
		return postRepository.findPostByUserId(userId).stream()
			.map(post -> getDefaultInfo(post, getOptionListByMyCarId(post.getMycarId())))
			.collect(Collectors.toList());
	}

	private List<DrivingInfo> getDrivingInfoList(Long userId) {
		return getPostListByTesterId(userId).stream()
			.map(post -> DrivingInfo.builder()
				.post(getDefaultInfo(post, getOptionListByMyCarId(post.getId())))
				.date(getDate(userId, post.getId()))
				.build())
			.collect(Collectors.toList());
	}

	private DefaultInfo getDefaultInfo(Post post, List<OptionDto> optionDtoList) {
		Car car = carRepository.findCarByPostId(post.getId())
			.orElseThrow(IllegalArgumentException::new);
		return post.toDefaultInfo(optionDtoList, car.getCarName(), getLocation(post.getId()), car.getCarImageUrl());
	}

	private List<Post> getPostListByTesterId(Long userId) {
		return postRepository.findPostByTesterId(userId);
	}

	private String getDate(Long userId, Long postId) {
		return postRepository.findDateByPostIdAndUserId(postId, userId).orElse("");
	}

	private LocationDto getLocation(Long postId) {
		return postRepository.findLocationByPostId(postId)
			.map(Location::toDto)
			.orElse(LocationDto.builder().build());
	}

	private List<OptionDto> getOptionListByMyCarId(Long myCarId) {
		return myCarRepository.findOptionsByMyCarId(myCarId)
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
