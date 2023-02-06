package com.tdd.backend.post;

import java.util.List;

public enum SpecificCarOptions {
	IONIC6(List.of(RenderingOptions.LPG, RenderingOptions.LAMP)),
	IONIC5(List.of(RenderingOptions.BLUE, RenderingOptions.RED, RenderingOptions.LAMP, RenderingOptions.SUNROOF)),
	NEXO(List.of(RenderingOptions.BLUE, RenderingOptions.RED, RenderingOptions.MONITOR, RenderingOptions.SUNROOF, RenderingOptions.LAMP));

	private final List<RenderingOptions> optionList;

	SpecificCarOptions (List<RenderingOptions> options) {
		this.optionList = options;
	}

	//TODO: 올바르지 않은 차 이름이 인자로 넘어올 경우 예외처리
	public static SpecificCarOptions getCar(String name) {
		return valueOf(name);
	}

	public List<RenderingOptions> getOptions() {
		return optionList;
	}

}
