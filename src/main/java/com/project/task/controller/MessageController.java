package com.project.task.controller;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
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
	private KafkaTemplate<String, Message> kafkaTemplate;
	
	@Autowired
	private TreeMap<String, Message> messagesById;
	
	@Autowired
	private TreeMap<Long, Message> messageByReceivedTS;
	
	
	@Value(value = "${bcentral.application.topic.name}")
	private String topicName;
	
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {
		try {
			message.validate();
			kafkaTemplate.send(topicName, message);
			return ResponseEntity.accepted().body(message);
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse JSON", ex);
		}
	}

/*-- 	
 	@GetMapping("{id}")
 	public Optional<Message> getMessageById(@PathVariable("id") Long id) {
		return null; //	
 	}
--*/
	
	@GetMapping()
	public ResponseEntity<String> getMessage() {
		if (messageByReceivedTS.size() == 0)
			return ResponseEntity.internalServerError().body("No items in queue");
		
		try {
			Entry<Long, Message> e = messageByReceivedTS.firstEntry();
			ObjectMapper mapper = new ObjectMapper();
			String body = mapper.writeValueAsString(e.getValue());
			messageByReceivedTS.remove(e.getKey());
			return ResponseEntity.accepted().body(body);
		} catch (JsonProcessingException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse JSON", ex);
		}
	}
 	
 	@PutMapping("{Id}")
 	public void updateMessage(@PathVariable("id") Long id) {
 	}
}
