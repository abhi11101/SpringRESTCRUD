package com.abhi.crm.exceptionhandling;


public class CustomerException extends RuntimeException{

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}

	
	
}
