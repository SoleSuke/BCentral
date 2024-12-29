package com.project.task.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.task.model.Message;

@RestController
@RequestMapping("api/message")
public class MessageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping
	public Message createMessage(@RequestBody Message message) {
//		kafkaTemplate.send(message);
		return message;
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
