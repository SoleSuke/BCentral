package com.project.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.task.model.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	public void addMessage(Message message) {
	}
	
	public getMessage() {
		return null;  /*--Como hacer que sea FIFO? --*/
	}
	
	public Iterable<Message> updateMessage(long Id){
		return null;
	}
}
