package com.tdd.backend.render;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.backend.post.Category;
import com.tdd.backend.render.data.Option;
import com.tdd.backend.render.data.OptionResponse;

@Service
public class RenderService {
	public List<OptionResponse> getOptions(String carName) {
		List<RenderingOptions> list = SpecificCarOptions.getCar(carName).getOptions();
		List<OptionResponse> response = new ArrayList<>();

		for (Category category : Category.values()) {
			List<Option> optionList = new ArrayList<>();

			for (RenderingOptions renderOption : list) {
				if (isCategoryEquals(category, renderOption.getCategory())) {
					Option option = new Option(renderOption.getName(), renderOption.getCategory().getName());
					optionList.add(option);
				}
			}
			if (!optionList.isEmpty())
				response.add(OptionResponse.builder().category(category.getName()).options(optionList).build());

		}

		return response;
	}

	private boolean isCategoryEquals(Category A, Category B) {
		return A.equals(B);
	}

}
