package com.tdd.backend.post.service;

import org.springframework.stereotype.Service;

import com.tdd.backend.post.data.SharingDto;
import com.tdd.backend.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SharingService {
	private final PostRepository postRepository;

	public void save(SharingDto sharingDto) {
		postRepository.save(sharingDto.toEntity());
	}

}
