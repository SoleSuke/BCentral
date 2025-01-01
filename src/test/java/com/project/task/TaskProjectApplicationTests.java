package com.project.task;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
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
	
	@Autowired
	private TreeMap<String, Message> messagesById;
	
	@Autowired
	private TreeMap<Long, Message> messageByReceivedTS;
	
	@Test
	public void testNotFound() throws Exception {
		System.out.println("------------------------------TEST_NOT_FOUND------------------------------");
		System.out.println();
		Message msg = Message.builder().id("12345")
				.timestamp("2024-12-23T11:19:32Z")
				.message("Este es un mensaje de prueba")
				.metadata(Metadata.builder().source("app1").type("notification").build()).build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is4xxClientError())
				.andReturn().getResponse();

		messagesById.clear();
		messagesById.clear();
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
		
	}
	
	@Test
	public void testSuccessfullPostAndGet() throws Exception {
		System.out.println("-------------------------TEST_POST_AND_GET_OK-----------------------------");
		System.out.println();
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
		TimeUnit.SECONDS.sleep(2);
		response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andReturn().getResponse();
		
		messagesById.clear();
		messagesById.clear();
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
	
	}
	
	@Test
	public void testPostMessageIncorrectTimestampFormat() throws Exception {
		System.out.println("--------------------TEST_POST_MSG_WRONG_TIME_FORMAT-----------------------");
		System.out.println("");
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
		
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
	
	}
	
	@Test
	public void testPostIncorrectMessage() throws Exception {
		System.out.println("------------------------TEST_POST_WRONG_MSG-------------------------------");
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
		
		messagesById.clear();
		messagesById.clear();
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
		
	}
	
	@Test
	public void testGetFailure() throws Exception {
		System.out.println("-------------------------------TEST_POST_WRONG_MSG-------------------------");
		MockHttpServletResponse response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is5xxServerError())
		.andReturn().getResponse();
		
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
		
	}
	
	@Test
	public void testSuccessfullPostPostAndGet() throws Exception {
		System.out.println("---------------------TEST_POST1_POST2_AND_GET_OK--------------------------");
		System.out.println();
		Message msg = Message.builder().id("45678")
				.timestamp("2024-12-29T18:19:32Z")
				.message("Este es un primer mensaje de prueba")
				.metadata(Metadata.builder().source("app1").type("notification").build()).build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();
		TimeUnit.SECONDS.sleep(2);
		
		msg = Message.builder().id("45679")
				.timestamp("2024-12-29T18:20:33Z")
				.message("Este es un segundo mensaje de prueba")
				.metadata(Metadata.builder().source("app2").type("notification").build()).build();
		
		response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();
		TimeUnit.SECONDS.sleep(2);
		
		response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		
		messagesById.clear();
		messagesById.clear();
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
	
	}
	
	@Test
	public void testSuccessfullPostPostPutAndGet() throws Exception {
		System.out.println("---------------------TEST_POST1_POST2_PUT1_AND_GET_OK--------------------------");
		System.out.println();
		Message msg = Message.builder().id("12345")
				.timestamp("2024-12-29T18:19:32Z")
				.message("Este es un primer mensaje de prueba")
				.metadata(Metadata.builder().source("app1").type("notification").build()).build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/api/message")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();
		TimeUnit.SECONDS.sleep(2);
		
		msg = Message.builder().id("45679")
				.timestamp("2024-12-29T18:20:33Z")
				.message("Este es un segundo mensaje de prueba")
				.metadata(Metadata.builder().source("app2").type("notification").build()).build();
		
		response = mockMvc.perform(put("/api/message/12345")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(msg)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();
		TimeUnit.SECONDS.sleep(2);
		
		response = mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		
		messagesById.clear();
		messagesById.clear();
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");
	
	}
	
}
