package com.tdd.backend.post;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tdd.backend.user.User;
import com.tdd.backend.user.UserRepository;

@SpringBootTest
class PostServiceTest {

	@Autowired
	private PostService postService;
	private User user;

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setUp() {
		user = new User("hado", "01010", "glory");
		userRepository.save(user);
	}

	@Test
	void save_with_option_location() throws Exception {

		Set<Option> options = new HashSet<>();
		Option option1 = new Option("option1", Category.ENGINE);
		Option option2 = new Option("option2", Category.COLOR);
		options.add(option1);
		options.add(option2);

		Location location = new Location("50", "40");

		Post post = new Post(user.getId(), false, DriveCareer.OVER_FIVE, "없음", options, location);


		//when
		postService.save(post);

	}
}