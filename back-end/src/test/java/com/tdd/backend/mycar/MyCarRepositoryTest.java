package com.tdd.backend.mycar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.mypage.data.MyCarDto;
import com.tdd.backend.mypage.data.MyCarResponse;
import com.tdd.backend.mypage.repository.MyCarRepository;
import com.tdd.backend.user.data.User;
import com.tdd.backend.user.repository.UserRepository;

@SpringBootTest
@Transactional
public class MyCarRepositoryTest {
	@Autowired
	private MyCarRepository myCarRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("mycar 등록 정상작동 테스트")
	void saveMyCarTest() {

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

		myCarRepository.save(myCarDto.toEntity());
		List<MyCarResponse> carInfoList = myCarRepository.getCarInfoByUserId(user.getId());
		Assertions.assertThat(carInfoList.size()).isEqualTo(1);
	}
}
