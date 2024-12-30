package com.project.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.task.model.Message;
import com.project.task.model.Metadata;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class TaskProjectApplicationJUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testPostMessage() throws Exception {
		Message msg = Message.builder().id(1L).timestamp("00:00").message("lala").metadata(Metadata.builder().source("mySource").type("myType").build()).build();
		ObjectMapper objectMapper = new ObjectMapper();
		
		mockMvc.perform(get("/api/message")).andDo(print());
		
		mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print());
	}
	
}
