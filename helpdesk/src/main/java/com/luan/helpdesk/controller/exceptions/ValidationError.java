package com.luan.helpdesk.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer statu, String error, String message, String patch) {
		super(timestamp, statu, error, message, patch);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void AddErro(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}
