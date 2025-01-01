package com.project.task.controller;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.lang.runtime.*;

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

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("api/message")
@CommonsLog
public class MessageController {

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;
	
	@Autowired
	private TreeMap<String, Message> messagesById;
	
	@Autowired
	private TreeMap<Long, Message> messagesByReceivedTS;
	
	
	@Value(value = "${bcentral.application.topic.name}")
	private String topicName;
	
	
	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {
		log.debug("POST request with body: " + message);
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
		log.debug("GET request");
		if (messagesByReceivedTS.size() == 0)
			return ResponseEntity.internalServerError().body("No items in queue");
		
		try {
			Entry<Long, Message> e = messagesByReceivedTS.firstEntry();
			ObjectMapper mapper = new ObjectMapper();
			String body = mapper.writeValueAsString(e.getValue());
			messagesByReceivedTS.remove(e.getKey());
			messagesById.remove(e.getValue().getId());
			return ResponseEntity.accepted().body(body);
		} catch (JsonProcessingException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse JSON", ex);
		}
	}
 	
 	@PutMapping("{Id}")
 	public ResponseEntity<Message> updateMessage(@PathVariable("Id") String id, @RequestBody Message message) {
 		log.info("PUT request ID: " + id + ", with body: " + message);
 		Message msg = messagesById.get(id);
 		if ( (messagesByReceivedTS.size() == 0) || ( msg == null))
 		{
 			try {
 				message.validate();
 				kafkaTemplate.send(topicName, message);
 				log.debug("PUT request created a new message: " + message);
 				return ResponseEntity.accepted().body(message);
 			} catch (RuntimeException ex) {
 				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse JSON", ex);
 			}
 		} else {
 			

			try {
 				message.validate();
 				// Remove message from Maps
 				messagesById.remove(id);
 				for (Entry<Long, Message> e: messagesByReceivedTS.entrySet()) {
 	 				if (e.getValue().getId().compareTo(id) == 0) { 
 	 					messagesByReceivedTS.remove(e.getKey());
 	 					break;
 	 				}
 	 			}
 				//////////////////////
 				kafkaTemplate.send(topicName, message);
 				log.debug("PUT request update a message with this: " + message.getMessage());
 				return ResponseEntity.accepted().body(message);
 			} catch (RuntimeException ex) {
 				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse JSON", ex);
 			}			 			
 		}
 	}
}
