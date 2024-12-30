package com.project.task;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
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
	public void testSuccessfullPostAndGet() throws Exception {
		Message msg = Message.builder().id("12345")
				.timestamp("2024-12-23T11:19:32Z")
				.message("Este es un mensaje de prueba")
				.metadata(Metadata.builder().source("app1").type("notification").build()).build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();
		TimeUnit.SECONDS.sleep(1);
		response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andReturn().getResponse();
		
		
	}
	
	@Test
	public void testPostMessageIncorrectTimestampFormat() throws Exception {
		Message msg = Message.builder().id("12345")
				.timestamp("2024-12-23T11")
				.message("Este es un mensaje de prueba")
				.metadata(Metadata.builder().source("app1").type("notification").build()).build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is5xxServerError())
				.andReturn().getResponse();
		
	}
	
	@Test
	public void testPostIncorrectMessage() throws Exception {
		WrongClass msg = WrongClass.builder().myId(1L).strField("LALA").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is5xxServerError())
				.andReturn().getResponse();
		
		TimeUnit.SECONDS.sleep(1);
		response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is5xxServerError())
		.andReturn().getResponse();
		
	}
	
	@Test
	public void testGetFailure() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is5xxServerError())
		.andReturn().getResponse();
		
		
		
	}
	
}
