package com.project.task.model;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private String id;
	private String timestamp;
	private String message;
	private Metadata metadata;
	
	public void validate() {
		if(id == null || timestamp == null || message == null || metadata == null)
			throw new NullPointerException("Values are null");
		DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
		formatter.parseDateTime(timestamp);
	}

	public String getId() {
		return id;
	}
	
}
