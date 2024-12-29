package com.project.task.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.task.exception.MessageNotFoundException;

@ControllerAdvice
public class MessageGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {MessageNotFoundException.class})
	public ResponseEntity<?> handleMessageNotFound(MessageNotFoundException messageNotFoundException, WebRequest request) {
		return handleExceptionInternal(messageNotFoundException, 
				messageNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
