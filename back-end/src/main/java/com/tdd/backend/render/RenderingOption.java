package com.tdd.backend.render;

import static com.tdd.backend.post.Category.*;

import com.tdd.backend.post.Category;

import lombok.Getter;

@Getter
public enum RenderingOption {
	LAMP(SAFETY, "LED 헤드램프"), //Safety option
	SUNROOF(SAFETY, "썬루프"), //Safety option
	MONITOR(DISPLAY, "모니터"),  //Display option
	RED(COLOR, "RED"), //Color
	BLUE(COLOR, "BLUE"), //color
	DIESEL(ENGINE, "디젤"), //engine
	LPG(ENGINE, "LPG"); //engine

	private final Category category;
	private final String name;

	RenderingOption(Category category, String name) {
		this.category = category;
		this.name = name;
	}

}
