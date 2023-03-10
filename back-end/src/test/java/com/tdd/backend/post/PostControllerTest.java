package com.tdd.backend.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.backend.auth.jwt.JwtProvider;
import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.data.MyCarDto;
import com.tdd.backend.mypage.model.MyCar;
import com.tdd.backend.mypage.repository.MyCarRepository;
import com.tdd.backend.post.data.LocationDto;
import com.tdd.backend.post.data.SharingDto;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;
import com.tdd.backend.post.repository.PostRepository;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.repository.UserRepository;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PostControllerTest {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MyCarRepository myCarRepository;
	@Autowired
	PostRepository postRepository;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("공유하기")
	void share() throws Exception {

		User user = User.builder()
			.email("hado@naver.com")
			.userName("young")
			.phoneNumber("01012341234")
			.userPassword("glory")
			.createdAt(LocalDate.now())
			.build();
		userRepository.save(user);

		List<OptionDto> options = new ArrayList<>();
		options.add(OptionDto.builder()
			.category("엔진")
			.name("opt1")
			.build());

		MyCarDto myCarDto = MyCarDto.builder()
			.carId(1L)
			.userId(user.getId())
			.options(options)
			.build();

		MyCar myCar = myCarDto.toEntity();

		myCarRepository.save(myCar);

		SharingDto sharingDto = SharingDto.builder()
			.rideOption(RideOption.RIDE_ALONE.toString())
			.requirement("hello")
			.myCarId(myCar.getId())
			.location(
				LocationDto.builder()
					.latitude("32.23423424")
					.longitude("127.123123")
					.build()
			)
			.dates(List.of("2022-10-23", "2023-10-11", "2023-02-11"))
			.build();
		String requestBody = objectMapper.writeValueAsString(sharingDto);

		mockMvc.perform(post("/sharing")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.header("Authorization", jwtProvider.generateAccessToken(user.getId(), user.getEmail())))
			.andExpect(status().isOk())
			.andDo(print());
		SoftAssertions soft = new SoftAssertions();
		List<Post> postByUserId = postRepository.findPostByUserId(user.getId());
		soft.assertThat(postByUserId.size()).isEqualTo(1);
		soft.assertThat(postByUserId.get(0).getRequirement()).isEqualTo("hello");
		soft.assertAll();
	}
}
