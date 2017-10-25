package org.vdc.boot.chat.controller.advice;

public class ClientError {
	private String status;
	private String message;

	public ClientError(String value, String message) {
		this.status = value;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
