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
	
	public void validateTimestamp() {
		DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
		formatter.parseDateTime(timestamp);
	}

	public String getId() {
		return id;
	}
	
}
