package com.example.exceptions;

public class DemosException extends Exception {
	private static final long serialVersionUID = 1L;

	public DemosException() {
		this("Error en la demo");
	}

	public DemosException(String message) {
		super(message);
	}

	public DemosException(Throwable cause) {
		this("Error en la demo", cause);
	}

	public DemosException(String message, Throwable cause) {
		super(message, cause);
	}

	public DemosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
