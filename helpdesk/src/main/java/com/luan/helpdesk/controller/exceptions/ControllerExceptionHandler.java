package com.luan.helpdesk.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> ValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
		
		ValidationError error = new ValidationError(System.currentTimeMillis(),
												HttpStatus.BAD_REQUEST.value(),
												"Validation error",
												"Erro na validação dos campos",
												request.getRequestURI()
				);
		
		for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
			error.AddErro(fe.getField(), fe.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
