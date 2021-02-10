package com.merchant.data;

public class SpaceWordDoesNotExistException extends DoesNotExistException {
	public SpaceWordDoesNotExistException() {
		super();
	}
	public SpaceWordDoesNotExistException(String message) {
		super(message);
	}

	public SpaceWordDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
