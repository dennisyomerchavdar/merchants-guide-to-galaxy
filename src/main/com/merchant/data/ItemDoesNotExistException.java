package com.merchant.data;

public class ItemDoesNotExistException extends DoesNotExistException {
	public ItemDoesNotExistException() {
		super();
	}
	public ItemDoesNotExistException(String message) {
		super(message);
	}

	public ItemDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
