package com.tdd.backend.car.service;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarService {
	private final CarRepository carRepository;

	public Long findCarId(String carName) {
		return carRepository.findIdByCarName(carName).orElseThrow(IllegalArgumentException::new);
	}
}
