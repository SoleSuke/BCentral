package com.project.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  Message{
	
	private Long id;
	private String timestamp;
	private String message;
	private Metadata metadata;
}
