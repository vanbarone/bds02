package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.ExceptionDatabase;
import com.devsuperior.bds02.services.exceptions.ExceptionEntityNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ExceptionEntityNotFound.class)
	public ResponseEntity<StandardError> entityNotFound(ExceptionEntityNotFound e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ExceptionDatabase.class)
	public ResponseEntity<StandardError> database(ExceptionDatabase e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
