package com.tdd.backend.post;

import java.time.LocalDateTime;
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
	void save_with_option_location_appointment() throws Exception {

		Set<Option> options = new HashSet<>();
		Option option1 = new Option("option1", Category.ENGINE);
		Option option2 = new Option("option2", Category.COLOR);
		options.add(option1);
		options.add(option2);

		Location location = new Location("50", "40");

		Set<Appointment> appointmentSet = new HashSet<>();
		Appointment appointment1 = new Appointment(LocalDateTime.now(), "PENDING");
		Appointment appointment2 = new Appointment(LocalDateTime.now().minusDays(1L), "PENDING");
		appointmentSet.add(appointment1);
		appointmentSet.add(appointment2);

		Post post = new Post(user.getId(), false, DriveCareer.OVER_FIVE,
			"없음", options, location, appointmentSet);


		//when
		postService.save(post);

	}
}