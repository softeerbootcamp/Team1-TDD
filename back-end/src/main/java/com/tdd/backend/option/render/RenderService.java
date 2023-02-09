package com.tdd.backend.option.render;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.backend.car.CarRepository;
import com.tdd.backend.option.Category;
import com.tdd.backend.option.data.OptionDto;
import com.tdd.backend.option.data.OptionResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RenderService {

	private final RenderRepository renderRepository;
	private final CarRepository carRepository;

	public List<OptionResponse> getOptions(String carName) {
		Long carId = carRepository.findIdByCarName(carName).orElseThrow(IllegalArgumentException::new);
		log.info("CarId : " + carId);

		List<RenderOption> renderOptionList = renderRepository.getCarIdByCarName(carId);
		List<OptionResponse> response = new ArrayList<>();

		for (Category category : Category.values()) {
			List<OptionDto> optionDtoList = new ArrayList<>();

			for (RenderOption renderOption : renderOptionList) {
				log.info("category : " + renderOption.getCategory());
				if (isCategoryEquals(category, renderOption.getCategory())) {
					OptionDto optionDto = new OptionDto(renderOption.getOptionName(), renderOption.getCategory());
					optionDtoList.add(optionDto);
				}
			}
			if (!optionDtoList.isEmpty()) {
				response.add(OptionResponse.builder().category(category.getName()).options(optionDtoList).build());
			}

		}

		return response;
	}

	private boolean isCategoryEquals(Category categoryA, String categoryB) {
		return categoryA.getName().equals(categoryB);
	}

}
