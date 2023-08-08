package com.blogproject.Payload;

import lombok.*;

@Data

public class ApiResponse {

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	private String message;

	public ApiResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	private boolean success;
}
