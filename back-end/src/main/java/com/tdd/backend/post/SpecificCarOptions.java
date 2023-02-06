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

}
