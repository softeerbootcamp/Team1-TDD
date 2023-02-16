package com.tdd.backend.car.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.CarDto;
import com.tdd.backend.car.exception.CarNotFoundException;
import com.tdd.backend.car.model.Car;
import com.tdd.backend.car.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarService {
	private final CarRepository carRepository;

	public Long findCarId(String carName) {
		return carRepository.findIdByCarName(carName).orElseThrow(CarNotFoundException::new);
	}

	public List<CarDto> findAllCar() {
		List<Car> carList = carRepository.findAllCar();

		return carList.stream()
			.map(Car::teCarDto)
			.collect(Collectors.toList());
	}
}
