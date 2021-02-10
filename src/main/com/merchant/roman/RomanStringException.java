package com.merchant.roman;

public class RomanStringException extends RuntimeException{
	public RomanStringException() {
		super();
	}
	public RomanStringException(String message) {
		super(message);
	}

	public RomanStringException(String message, Throwable cause) {
		super(message, cause);
	}
}
