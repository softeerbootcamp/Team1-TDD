package com.tdd.backend.post;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.car.model.Category;
import com.tdd.backend.post.model.Appointment;
import com.tdd.backend.post.model.AppointmentStatus;
import com.tdd.backend.post.model.Location;
import com.tdd.backend.post.model.Option;
import com.tdd.backend.post.model.Post;
import com.tdd.backend.post.model.RideOption;
import com.tdd.backend.post.repository.PostRepository;
import com.tdd.backend.post.service.DrivingService;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.repository.UserRepository;

@SpringBootTest
@Transactional
public class PostRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DrivingService drivingService;

	@Autowired
	PostRepository postRepository;

	@Test
	@DisplayName("모든 인자가 NULL이 아닐 때 쿼리 정상작동 테스트")
	void findByPostsIdTest() {

		//given
		User user = User.builder()
			.email("hado@naver.com")
			.userName("young")
			.phoneNumber("01012341234")
			.userPassword("glory")
			.createdAt(LocalDate.now())
			.build();

		userRepository.save(user);

		User user1 = User.builder()
			.email("yaho@naver.com")
			.userName("woong")
			.phoneNumber("01012341334")
			.userPassword("sdfsd")
			.createdAt(LocalDate.now())
			.build();

		userRepository.save(user1);

		User user2 = User.builder()
			.email("qwer@naver.com")
			.userName("qwer")
			.phoneNumber("01012341034")
			.userPassword("qqqwwweee")
			.createdAt(LocalDate.now())
			.build();

		userRepository.save(user2);

		User tester = User.builder()
			.email("do@naver.com")
			.userName("gskim")
			.phoneNumber("01013331234")
			.userPassword("awed")
			.createdAt(LocalDate.now())
			.build();

		userRepository.save(tester);

		Set<Option> optionSet = new HashSet<>();
		optionSet.add(new Option("가솔린 2.5", Category.ENGINE));
		optionSet.add(new Option("2WD", Category.DRIVING_SYSTEM));
		optionSet.add(new Option("Prestige", Category.CAR_MODEL));
		optionSet.add(new Option("5인승", Category.CAR_BODY_TYPE));
		optionSet.add(new Option("헤드업 디스플레이", Category.CAR_OPTION));
		optionSet.add(new Option("현대 스마트 센스 III", Category.CAR_OPTION));
		optionSet.add(new Option("플래티넘 III", Category.CAR_OPTION));

		Location location = new Location("20.12", "30.4432");

		Set<Appointment> appointmentSet = new HashSet<>();
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-02"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-03"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-05"), AppointmentStatus.PENDING));

		Post post = new Post(
			user.getId(),
			RideOption.RIDE_ALONE,
			"SANTAFE",
			"hello",
			optionSet,
			location, appointmentSet
		);

		postRepository.save(post);

		optionSet = new HashSet<>();
		optionSet.add(new Option("가솔린 2.5", Category.ENGINE));
		optionSet.add(new Option("2WD", Category.DRIVING_SYSTEM));
		optionSet.add(new Option("Prestige", Category.CAR_MODEL));
		optionSet.add(new Option("5인승", Category.CAR_BODY_TYPE));
		optionSet.add(new Option("헤드업 디스플레이", Category.CAR_OPTION));
		optionSet.add(new Option("현대 스마트 센스 III", Category.CAR_OPTION));
		optionSet.add(new Option("빌트인 캠(보조배터리 포함)", Category.CAR_OPTION));

		location = new Location("20234.12", "30467.4432");

		appointmentSet = new HashSet<>();
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-02"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-06"), AppointmentStatus.PENDING));

		Post post1 = new Post(
			user2.getId(),
			RideOption.RIDE_ALONE,
			"SANTAFE",
			"hello world",
			optionSet,
			location, appointmentSet
		);

		postRepository.save(post1);

		optionSet = new HashSet<>();
		optionSet.add(new Option("가솔린 2.5", Category.ENGINE));
		optionSet.add(new Option("2WD", Category.DRIVING_SYSTEM));
		optionSet.add(new Option("Prestige", Category.CAR_MODEL));
		optionSet.add(new Option("5인승", Category.CAR_BODY_TYPE));
		optionSet.add(new Option("헤드업 디스플레이", Category.CAR_OPTION));
		optionSet.add(new Option("현대 스마트 센스 III", Category.CAR_OPTION));
		optionSet.add(new Option("빌트인 캠(보조배터리 포함)", Category.CAR_OPTION));
		optionSet.add(new Option("파킹 어시스트 I", Category.CAR_OPTION));

		location = new Location("2234.12", "307.4432");

		appointmentSet = new HashSet<>();
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-01"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-02"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-03"), AppointmentStatus.PENDING));
		appointmentSet.add(new Appointment(LocalDate.parse("2022-01-04"), AppointmentStatus.PENDING));

		Post post2 = new Post(
			tester.getId(),
			RideOption.RIDE_ALONE,
			"SANTAFE",
			"hello world",
			optionSet,
			location, appointmentSet
		);

		postRepository.save(post2);

		//when
		String carName = "SANTAFE";
		List<String> options = List.of("가솔린 2.5", "2WD", "헤드업 디스플레이", "빌트인 캠(보조배터리 포함)");
		List<String> dates = List.of("2022-01-02", "2022-01-03", "2022-01-04");

		//then
		SoftAssertions softAssertions = new SoftAssertions();

		softAssertions.assertThat(
			postRepository.findPostIdsByOptionsAndDatesAndCarName(options, dates, carName, options.size())
		).isEqualTo(List.of(2L, 3L));

		softAssertions.assertThat(
			postRepository.findPostIdsByOptionsAndCarName(options, carName, options.size())
		).isEqualTo(List.of(2L, 3L));

		softAssertions.assertThat(
			postRepository.findPostIdsByDatesAndCarName(dates, carName)
		).isEqualTo(List.of(1L, 2L, 3L));

		softAssertions.assertThat(
			postRepository.findPostIdsByCarName(carName)
		).isEqualTo(List.of(1L, 2L, 3L));

		softAssertions.assertAll();

	}

}
