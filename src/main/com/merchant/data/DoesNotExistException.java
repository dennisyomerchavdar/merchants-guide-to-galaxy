package com.merchant.data;

public class DoesNotExistException extends RuntimeException{
	public DoesNotExistException() {
		super();
	}
	public DoesNotExistException(String message) {
		super(message);
	}

	public DoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
