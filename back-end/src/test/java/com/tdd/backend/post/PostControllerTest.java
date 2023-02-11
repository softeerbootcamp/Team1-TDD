package com.tdd.backend.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.post.data.LocationDto;
import com.tdd.backend.post.data.PostDto;
import com.tdd.backend.post.data.SharingDto;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;
import com.tdd.backend.user.User;
import com.tdd.backend.user.UserRepository;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PostControllerTest {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	MockMvc mockMvc;

	@Test
	void sharingTest() throws Exception {

		User user = User.builder()
			.email("hado@naver.com")
			.userName("young")
			.phoneNumber("01012341234")
			.userPassword("glory")
			.build();
		userRepository.save(user);

		SharingDto sharingDto = SharingDto.builder()
			.post( PostDto.builder()
				.carName("Santafe")
				.requirement("hello")
				.userId(user.getId())
				.rideOption(RideOption.RIDE_ALONE.toString())
				.build())
			.location(LocationDto.builder()
				.latitude("32.23423424")
				.longitude("127.123123")
				.build())
			.options(List.of(OptionDto.builder().name("최고안전").category("안전/성능").build()))
			.dates(List.of("2022-10-23", "2023-10-11", "2023-02-11"))
			.build();
		String requestBody = objectMapper.writeValueAsString(sharingDto);

		mockMvc.perform(post("/sharing")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isFound())
			.andDo(print());
		SoftAssertions soft = new SoftAssertions();
		Post post = postRepository.findById(1L).get();
		soft.assertThat(post.getId()).isEqualTo(user.getId());
		soft.assertThat(post.getCarName()).isEqualTo("Santafe");
		soft.assertAll();
	}
}
