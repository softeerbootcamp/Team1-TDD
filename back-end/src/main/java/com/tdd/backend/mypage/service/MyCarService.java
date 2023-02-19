package com.tdd.backend.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.exception.CarNotFoundException;
import com.tdd.backend.car.repository.CarRepository;
import com.tdd.backend.mypage.MyCarRepository;
import com.tdd.backend.mypage.data.MyCarCreate;
import com.tdd.backend.mypage.data.MyCarDto;
import com.tdd.backend.mypage.data.MyCarResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyCarService {
	private final MyCarRepository myCarRepository;
	private final CarRepository carRepository;

	public void save(Long userId, MyCarCreate myCarCreate) {
		String carName = myCarCreate.getCarName();
		MyCarDto myCarDto = MyCarDto.builder()
			.userId(userId)
			.carId(carRepository.findIdByCarName(carName).orElseThrow(CarNotFoundException::new))
			.options(myCarCreate.getOptionDtoList())
			.build();

		myCarRepository.save(myCarDto.toEntity());
	}

	public List<MyCarResponse> getMyCarList(Long userId) {
		return myCarRepository.getCarInfoByUserId(userId);
	}
}
