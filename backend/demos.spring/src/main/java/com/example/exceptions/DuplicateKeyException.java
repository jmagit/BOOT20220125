package com.example.exceptions;

public class DuplicateKeyException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateKeyException() {
		this("Duplicate Key");
	}

	public DuplicateKeyException(String message) {
		super(message);
	}

	public DuplicateKeyException(Throwable cause) {
		super("Duplicate Key", cause);
	}

	public DuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
