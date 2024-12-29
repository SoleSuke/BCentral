package com.project.task.exception;

public class MessageNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5071646428281007896L;

	public MessageNotFoundException(String message) {
		super(message);
	}
}
