package com.tdd.backend.render;

import java.util.List;

public enum SpecificCarOption {
	IONIC6(List.of(RenderingOption.LPG, RenderingOption.LAMP)),
	IONIC5(List.of(RenderingOption.BLUE, RenderingOption.RED, RenderingOption.LAMP, RenderingOption.SUNROOF)),
	NEXO(List.of(RenderingOption.BLUE, RenderingOption.RED, RenderingOption.MONITOR, RenderingOption.SUNROOF, RenderingOption.LAMP));

	private final List<RenderingOption> optionList;

	SpecificCarOption(List<RenderingOption> options) {
		this.optionList = options;
	}

	//TODO: 올바르지 않은 차 이름이 인자로 넘어올 경우 예외처리
	public static SpecificCarOption getCar(String name) {
		return valueOf(name);
	}

	public List<RenderingOption> getOptions() {
		return optionList;
	}

}
