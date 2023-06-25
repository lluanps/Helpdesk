package com.luan.helpdesk.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.luan.helpdesk.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
		
		StandartError error = new StandartError(System.currentTimeMillis(),
												HttpStatus.NOT_FOUND.value(),
												"Object Not Found",
												ex.getMessage(),
												request.getRequestURI()
												);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> DataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		
		StandartError error = new StandartError(System.currentTimeMillis(),
												HttpStatus.BAD_REQUEST.value(),
												"Data violation",
												ex.getMessage(),
												request.getRequestURI()
												);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
