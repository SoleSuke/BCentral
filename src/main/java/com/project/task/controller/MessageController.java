package com.project.task.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.project.task.model.Message;

@RestController
@RequestMapping("api/message")
public class MessageController {

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;
	
	@Value(value = "${bcentral.application.topic.name}")
	private String topicName;
	
	private final HashMap<Long, Message> messages = new HashMap<>();
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {
		kafkaTemplate.send(topicName, message);
		return ResponseEntity.accepted().body(message);
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
 	
 	
    @KafkaListener(topics = "prueba")
    public void listenTopic(Message message) {
        System.out.println("Received Message: " + message);
    }
}
