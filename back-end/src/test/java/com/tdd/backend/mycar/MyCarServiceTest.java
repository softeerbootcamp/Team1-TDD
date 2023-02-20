package com.tdd.backend.mycar;

import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import com.tdd.backend.car.repository.CarRepository;
import com.tdd.backend.mypage.repository.MyCarRepository;
import com.tdd.backend.mypage.data.MyCarResponse;
import com.tdd.backend.mypage.service.MyCarService;

@ExtendWith(MockitoExtension.class)
@Transactional
public class MyCarServiceTest {
	@Mock
	private MyCarRepository myCarRepository;

	@Mock
	private CarRepository carRepository;

	@Test
	@DisplayName("내 차 불러오기 테스트")
	void getMyCarsTest() {
		// Given
		Long userId = 1L;

		String carName1 = "SANTAFE";
		Long myCarId1 = 1L;
		MyCarResponse myCarResponse1 = MyCarResponse.builder()
			.carName(carName1)
			.myCarId(myCarId1)
			.build();

		String carName2 = "PALASADE";
		Long myCarId2 = 2L;
		MyCarResponse myCarResponse2 = MyCarResponse.builder()
			.carName(carName2)
			.myCarId(myCarId2)
			.build();

		List<MyCarResponse> myCarList = List.of(myCarResponse1, myCarResponse2);
		when(myCarRepository.getCarInfoByUserId(userId)).thenReturn(myCarList);

		// When
		MyCarService myCarService = new MyCarService(myCarRepository, carRepository);
		List<MyCarResponse> actualMyCarList = myCarService.getMyCarList(userId);

		// Then
		SoftAssertions softAssertions = new SoftAssertions();

		softAssertions.assertThat(actualMyCarList).hasSize(2);
		softAssertions.assertThat(actualMyCarList.get(0).getCarName()).isEqualTo("SANTAFE");
		softAssertions.assertThat(actualMyCarList.get(0).getMyCarId()).isEqualTo(myCarResponse1.getMyCarId());
		softAssertions.assertThat(actualMyCarList.get(1).getCarName()).isEqualTo("PALASADE");
		softAssertions.assertThat(actualMyCarList.get(1).getMyCarId()).isEqualTo(myCarResponse2.getMyCarId());

		softAssertions.assertAll();
	}

}
