package com.tdd.backend.post;

import static com.tdd.backend.post.Category.*;

import java.util.Collections;
import java.util.List;

public enum Options {
	LAMP(SAFETY), //Safety option
	SUNROOF(SAFETY), //Safety option
	MONITOR(DISPLAY),  //Display option
	RED(COLOR), //Color
	BLUE(COLOR), //color
	DIESEL(ENGINE), //engine
	LPG(ENGINE); //engine

	Options(Category category) {
	}

	public List<Options> getCommonOptions() {
		return List.of(LAMP, SUNROOF, MONITOR);
	}

	public List getAvanteOptions() {
		return Collections.singletonList(List.of(RED, COLOR).addAll(getCommonOptions()));
	}

}
