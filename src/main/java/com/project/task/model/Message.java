package com.project.task.model;

import lombok.Data;

@Data
public class  Message{
	private Long id;
	private String timestamp;
	private String message;
	private Metadata metadata;
}
