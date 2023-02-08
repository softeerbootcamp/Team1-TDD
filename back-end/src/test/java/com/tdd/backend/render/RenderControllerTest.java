package com.tdd.backend.render;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.backend.option.render.RenderController;

@SpringBootTest
@AutoConfigureMockMvc
class RenderControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RenderController renderController;

	@Test
	@DisplayName("특정 차량의 이름의 주어졌을 때 그 차에 대한 옵션들을 반환")
	void getOptions() throws Exception {
		//given
		String request = "/NEXO";

		//expected
		mockMvc.perform(get("/options" + request))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
