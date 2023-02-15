package com.tdd.backend.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.data.DefaultInfo;
import com.tdd.backend.mypage.data.DrivingInfo;
import com.tdd.backend.mypage.data.MyPageResponse;
import com.tdd.backend.mypage.data.SharingInfo;
import com.tdd.backend.mypage.data.UserInfo;
import com.tdd.backend.mypage.exception.AppointmentNotFoundException;
import com.tdd.backend.mypage.exception.PostNotFoundException;
import com.tdd.backend.post.data.AppointmentDto;
import com.tdd.backend.post.model.Appointment;
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

	public MyPageResponse getMyPageInfo(Long userId) {
		// 시승했던 정보
		List<DrivingInfo> drivingInfoList = new ArrayList<>();
		List<Long> postIdList = postRepository.findPostIdByTesterId(userId);

		log.info(String.valueOf(postIdList));

		for (Long postId : postIdList) {
			List<OptionDto> optionDtoList = postRepository.findOptionByPostId(postId)
				.stream()
				.map(Option::toDto)
				.collect(Collectors.toList());
			DefaultInfo defaultInfo = postRepository.findById(postId)
				.orElseThrow(PostNotFoundException::new)
				.toDefaultInfo(optionDtoList);
			String date = postRepository.findDateByPostIdAndUserId(postId, userId)
				.orElseThrow(AppointmentNotFoundException::new);
			drivingInfoList.add(DrivingInfo.builder()
				.post(defaultInfo)
				.date(date)
				.build());
		}

		//공유하기 정보
		List<SharingInfo> sharingInfoList = new ArrayList<>();
		List<DefaultInfo> defaultInfoList = postRepository.findPostByUserId(userId)
			.stream()
			.map(post -> post.toDefaultInfo(postRepository.findOptionByPostId(post.getId())
				.stream()
				.map(Option::toDto)
				.collect(Collectors.toList())))
			.collect(Collectors.toList());
		log.info(String.valueOf(defaultInfoList.size()));
		defaultInfoList.forEach(defaultInfo -> sharingInfoList.add(SharingInfo.builder()
			.post(defaultInfo)
			.appointments(getAppointmentListByPostId(defaultInfo.getId()))
			.build()));

		//유저 정보
		UserInfo userInfo = userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new)
			.toUserInfo(sharingInfoList.size(), drivingInfoList.size());

		return MyPageResponse.builder()
			.user(userInfo)
			.sharing(sharingInfoList)
			.driving(drivingInfoList)
			.build();
	}

	private List<AppointmentDto> getAppointmentListByPostId(Long postId) {
		return postRepository.findAppointmentsByPostId(postId)
			.stream()
			.map(Appointment::toDto)
			.collect(Collectors.toList());
	}
}
