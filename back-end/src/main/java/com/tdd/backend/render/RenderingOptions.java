package com.tdd.backend.render;

import static com.tdd.backend.post.Category.*;

import com.tdd.backend.post.Category;

public enum RenderingOptions {
	LAMP(SAFETY), //Safety option
	SUNROOF(SAFETY), //Safety option
	MONITOR(DISPLAY),  //Display option
	RED(COLOR), //Color
	BLUE(COLOR), //color
	DIESEL(ENGINE), //engine
	LPG(ENGINE); //engine

	private final Category category;

	RenderingOptions(Category category) {
		this.category = category;
	}

	public String getCategory() {
		return category.toString();
	}

}
