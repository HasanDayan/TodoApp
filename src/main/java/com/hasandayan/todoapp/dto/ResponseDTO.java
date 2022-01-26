package com.hasandayan.todoapp.dto;

public class ResponseDTO {
	
	private String message;
	
	public ResponseDTO() {
		
	}

	public ResponseDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseDTO [message=").append(message).append("]");
		return builder.toString();
	}
	
}
