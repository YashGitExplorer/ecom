package com.ecom.utility;


public class ApiResponse<T> {
	
	public ApiResponse(String message, int status, T data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
	private String message;
	private int status;
	private T data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	 public static <T> ApiResponse<T> of(String message, int statusCode, T data) {
	        return new ApiResponse<>(message, statusCode, data);
	    }

}
