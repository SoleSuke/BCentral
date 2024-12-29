package com.project.task.service;

import com.project.task.model.Message;

public interface MessageService {
	void addMessage(Message message);
	Message updateMessage(long Id);
	Message getMessage();  
}

