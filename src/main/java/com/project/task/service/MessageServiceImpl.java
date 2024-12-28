package com.project.task.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import com.project.task.model.msjJSON;
import com.project.task.controller.controlMSJ;
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	public void addMessage(msjJSON messageJSON) {
		this.messageJSON.id=messageJSON;
		this.messageJSON.timestamp=messageJSON.getTime();
		this.messageJSON<source, type>=messageJSON.<source, type>;
		controlMSJ.addMSJ(this.messageJSON);
	}
	
	public msjJSON getMessage() {
		return controlMSJ.getMSJ();
	}
	
	public Iterable<msjJSON> updateMessage(long Id){
		return controlMSJ.putMSJ(Id);
	}
}
