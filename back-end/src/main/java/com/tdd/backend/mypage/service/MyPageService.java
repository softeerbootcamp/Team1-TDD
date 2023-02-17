package com.tdd.backend.mypage.service;

import java.util.ArrayList;
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

	private UserInfo getUserInfo(Long userId, List<DrivingInfo> drivingInfoList, List<SharingInfo> sharingInfoList) {
		return userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new)
			.toUserInfo(sharingInfoList.size(), drivingInfoList.size());
	}

	private List<SharingInfo> getSharingInfoList(Long userId) {
		List<SharingInfo> sharingInfoList = new ArrayList<>();
		List<DefaultInfo> defaultInfoList = postRepository.findPostByUserId(userId)
			.stream()
			.map(post ->
				post.toDefaultInfo(getOptionListByPostId(post.getId()),
					getLocation(post.getId()),
					getImageUrl(post.getCarName())))
			.collect(Collectors.toList());
		log.info(String.valueOf(defaultInfoList.size()));

		defaultInfoList.forEach(defaultInfo -> sharingInfoList.add(SharingInfo.builder()
			.post(defaultInfo)
			.appointments(getAppointmentListByPostId(defaultInfo.getId()))
			.build()));

		return sharingInfoList;
	}

	private String getImageUrl(String carName) {
		return carRepository.findImageUrlByName(carName)
			.orElse("");
	}

	private List<DrivingInfo> getDrivingInfoList(Long userId) {
		List<DrivingInfo> drivingInfoList = new ArrayList<>();
		List<Long> postIdList = postRepository.findPostIdByTesterId(userId);
		log.info(String.valueOf(postIdList));

		for (Long postId : postIdList) {
			List<OptionDto> optionDtoList = getOptionListByPostId(postId);

			DefaultInfo defaultInfo = postRepository.findById(postId)
				.map(post ->
					post.toDefaultInfo(optionDtoList,
						getLocation(postId),
						getImageUrl(post.getCarName())))
				.orElse(DefaultInfo.builder().build());

			String date = postRepository.findDateByPostIdAndUserId(postId, userId)
				.orElse("");

			drivingInfoList.add(DrivingInfo.builder()
				.post(defaultInfo)
				.date(date)
				.build());
		}

		return drivingInfoList;
	}

	private LocationDto getLocation(Long postId) {
		return postRepository.findLocationByPostId(postId)
			.map(Location::toDto)
			.orElse(LocationDto.builder().build());
	}

	private List<OptionDto> getOptionListByPostId(Long id) {
		return postRepository.findOptionByPostId(id)
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
