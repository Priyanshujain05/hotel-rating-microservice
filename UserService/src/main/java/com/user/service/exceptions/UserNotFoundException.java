package com.user.service.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public UserNotFoundException(String msg) {
		super();
		this.msg = msg;
	}



	public String getMessage() {
		return msg;
	}
}
