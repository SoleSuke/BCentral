package com.project.task.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.task.model.Message;
import com.project.task.service.MessageService;

@RestController
public class MessageController {
 	
 	private  MessageService messageService;
 	
 	@Autowired
 	public MessageController(MessageService messageService) {
 		this.messageService = messageService;
 	}
 	
 	@GetMapping 
 	public getMessage() {   //no creo que deba ser iterable
		return messageService.getMessage();  // Como hago que sea FIFO el msj consumido	
 	}
 	
 	@PostMapping
 	public addMessage(Message message) {
 		return messageService.addMessage(message);
 	}
 	
 	@PutMapping("{Id}")
 	public void updateMessage(long id) {
 		messageService.updateMessage(id);
 	}
}
