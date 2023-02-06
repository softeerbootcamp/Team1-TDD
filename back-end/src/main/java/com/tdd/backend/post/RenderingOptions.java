package com.tdd.backend.post;

import static com.tdd.backend.post.Category.*;

public enum RenderingOptions {
	LAMP(SAFETY), //Safety option
	SUNROOF(SAFETY), //Safety option
	MONITOR(DISPLAY),  //Display option
	RED(COLOR), //Color
	BLUE(COLOR), //color
	DIESEL(ENGINE), //engine
	LPG(ENGINE); //engine

	RenderingOptions(Category category) {
	}

}
