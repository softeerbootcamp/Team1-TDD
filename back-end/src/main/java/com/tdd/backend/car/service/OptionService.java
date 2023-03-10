package com.tdd.backend.car.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.data.OptionDto;
import com.tdd.backend.car.data.OptionResponse;
import com.tdd.backend.car.model.Category;
import com.tdd.backend.car.model.Option;
import com.tdd.backend.car.repository.OptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OptionService {

	private final OptionRepository optionRepository;

	public List<OptionResponse> getOptions(Long carId) {
		List<Option> carOptionList = optionRepository.findCarOptionList(carId);
		List<OptionResponse> response = new ArrayList<>();

		for (Category category : Category.values()) {
			List<OptionDto> optionDtoList = new ArrayList<>();

			for (Option carOption : carOptionList) {
				if (isCategoryEquals(category, carOption.getCategoryName())) {
					OptionDto optionDto = OptionDto.builder()
						.name(carOption.getOptionName())
						.category(carOption.getCategoryName())
						.build();
					optionDtoList.add(optionDto);
				}
			}
			if (!optionDtoList.isEmpty()) {
				response.add(OptionResponse.builder()
					.category(category.getName())
					.options(optionDtoList).build());
			}
		}

		return response;
	}

	private boolean isCategoryEquals(Category categoryA, String categoryB) {
		return categoryA.getName().equals(categoryB);
	}

}
