package com.project.task.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import com.project.task.model.Message;
import com.project.task.controller.MessageController;
@Service
public class MessageServiceImpl implements MessageService {
	private MessageController messagecontroller;
	@Autowired
	public void addMessage(Message message) {
		this.message.id=messageJSON;
		this.message.timestamp=message.getTime();
		this.message.metadata<source, type>=message.metadata<source, type>;
		messagecontroller.addMessage(this.message);
	}
	
	public getMessage() {
		return messagecontroller.getMessage();  /*--Como hacer que sea FIFO? --*/
	}
	
	public updateMessage(long Id){
		return messagecontroller.putMessage(Id);
	}
}
