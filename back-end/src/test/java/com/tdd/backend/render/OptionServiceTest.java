package com.tdd.backend.render;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tdd.backend.car.data.CarDto;
import com.tdd.backend.car.model.Car;
import com.tdd.backend.car.repository.CarRepository;
import com.tdd.backend.car.service.CarService;

@ExtendWith(MockitoExtension.class)
public class OptionServiceTest {

	@Mock
	private CarRepository carRepository;

	@Test
	@DisplayName("모든 차량의 정보를 반환하는지 테스트")
	void findAllCarTest() {
		// Given
		Car car = Car.builder()
			.carName("Test Car 1")
			.carKorName("테스트 카 1")
			.carImageUrl("http://test-car-1.jpg")
			.build();

		Car car2 = Car.builder()
			.carName("Test Car 2")
			.carKorName("테스트 카 2")
			.carImageUrl("http://test-car-2.jpg")
			.build();

		List<Car> carList = List.of(car, car2);
		when(carRepository.findAllCar()).thenReturn(carList);

		// When
		CarService carService = new CarService(carRepository);
		List<CarDto> actualCarDtoList = carService.findAllCar();

		// Then
		assertThat(actualCarDtoList).hasSize(2);

		CarDto expected1 = CarDto.builder()
			.carName("Test Car 1")
			.carKorName("테스트 카 1")
			.carImageUrl("http://test-car-1.jpg")
			.build();

		CarDto expected2 = CarDto.builder()
			.carName("Test Car 2")
			.carKorName("테스트 카 2")
			.carImageUrl("http://test-car-2.jpg")
			.build();

		assertThat(actualCarDtoList.get(0)).extracting(
			CarDto::getCarId,
			CarDto::getCarName,
			CarDto::getCarKorName,
			CarDto::getCarImageUrl
		).containsExactly(
			expected1.getCarId(),
			expected1.getCarName(),
			expected1.getCarKorName(),
			expected1.getCarImageUrl()
		);

		assertThat(actualCarDtoList.get(1)).extracting(
			CarDto::getCarId,
			CarDto::getCarName,
			CarDto::getCarKorName,
			CarDto::getCarImageUrl
		).containsExactly(
			expected2.getCarId(),
			expected2.getCarName(),
			expected2.getCarKorName(),
			expected2.getCarImageUrl());
	}
}
