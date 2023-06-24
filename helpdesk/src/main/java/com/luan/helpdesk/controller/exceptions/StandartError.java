package com.luan.helpdesk.controller.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer statu;
	private String error;
	private String message;
	private String patch;

	public StandartError() {
		super();
	}

	public StandartError(Long timestamp, Integer statu, String error, String message, String patch) {
		super();
		this.timestamp = timestamp;
		this.statu = statu;
		this.error = error;
		this.message = message;
		this.patch = patch;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

}
