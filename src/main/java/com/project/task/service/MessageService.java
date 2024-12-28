package com.project.task.service;

import com.project.task.model.msjJSON;

import java.util.Optional;

public interface MessageService {
	void addMessage(msjJSON messageJSON);
    Iterable<msjJSON> updateMessage(long Id);
    msjJSON getMessage();  
}

