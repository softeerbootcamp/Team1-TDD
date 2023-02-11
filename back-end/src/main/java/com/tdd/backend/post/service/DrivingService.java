package com.tdd.backend.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.post.PostRepository;
import com.tdd.backend.post.data.SharingDto;
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

	public SharingDto getSharingDateByPostId(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		List<Option> options = postRepository.findOptionByPostId(postId);
		List<String> dates = postRepository.findAppointmentByPostId(postId);
		Optional<Location> location = postRepository.findLocationByPostId(postId);
		List<OptionDto> optionDtos = options.stream().map(Option::toDto).collect(Collectors.toList());
		return SharingDto.builder()
			.post(post.orElseThrow(RuntimeException::new).toDto())
			.options(optionDtos)
			.dates(dates)
			.location(location.orElseThrow(RuntimeException::new).toDto())
			.build();
	}
}
