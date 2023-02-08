package com.tdd.backend.render;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.backend.post.Category;
import com.tdd.backend.render.data.OptionDto;
import com.tdd.backend.render.data.OptionResponse;

@Service
public class RenderService {
	public List<OptionResponse> getOptions(String carName) {
		List<EntireOption> entireOptionList = SpecificCarOption.getCar(carName).getOptions();
		List<OptionResponse> response = new ArrayList<>();

		for (Category category : Category.values()) {
			List<OptionDto> optionDtoList = new ArrayList<>();

			for (EntireOption renderOption : entireOptionList) {
				if (isCategoryEquals(category, renderOption.getCategory())) {
					OptionDto optionDto = new OptionDto(renderOption.getName(), renderOption.getCategory().getName());
					optionDtoList.add(optionDto);
				}
			}
			if (!optionDtoList.isEmpty()) {
				response.add(OptionResponse.builder().category(category.getName()).options(optionDtoList).build());
			}

		}

		return response;
	}

	private boolean isCategoryEquals(Category categoryA, Category categoryB) {
		return categoryA.equals(categoryB);
	}

}
