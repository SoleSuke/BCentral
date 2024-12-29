package com.project.task.controller;

import java.util.Optional;

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

        @PostMapping
        public Message createMessage(@RequestBody Message message) {
                return message;
        }

 	
 	@GetMapping("{id}")
 	public Optional<Message> getMessageById(@PathVariable("id") Long id) {
		return null; //	
 	}

	@GetMapping()
        public Message getMessage() {
                return message; // Debo asegurar que sea FIFO
        }
 	
 	@PutMapping("{Id}")
 	public void updateMessage(@PathVariable("id") Long id) {
 	}
}
