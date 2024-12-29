package com.project.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.task.model.Message;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	public void addMessage(Message message) {
	}
	
	public Message getMessage() {
		return null;  /*--Como hacer que sea FIFO? --*/
	}
	
	public Message updateMessage(long Id){
		return null;
	}
}
