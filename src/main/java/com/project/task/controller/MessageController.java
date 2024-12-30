package com.project.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.task.model.Message;

@RestController
@RequestMapping("api/message")
public class MessageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value(value = "${bcentral.application.topic.name}")
	private String topicName;
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			kafkaTemplate.send(topicName, objectMapper.writeValueAsString(message));
			return ResponseEntity.accepted().body(message);
		} catch (JsonProcessingException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Parsing Error", ex);
		}
	}

/*-- 	
 	@GetMapping("{id}")
 	public Optional<Message> getMessageById(@PathVariable("id") Long id) {
		return null; //	
 	}
--*/
	
	@GetMapping()
	public Message getMessage() {
		return null;
	}
 	
 	@PutMapping("{Id}")
 	public void updateMessage(@PathVariable("id") Long id) {
 	}
}
