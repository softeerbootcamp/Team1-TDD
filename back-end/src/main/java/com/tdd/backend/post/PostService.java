package com.tdd.backend.post;

import org.springframework.stereotype.Service;

import com.tdd.backend.post.data.SharingDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public void save(SharingDto sharingDto) {
		postRepository.save(sharingDto.toEntity());
	}
}
