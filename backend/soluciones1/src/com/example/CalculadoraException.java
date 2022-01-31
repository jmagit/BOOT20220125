package com.example;

public class CalculadoraException extends Exception {

	public CalculadoraException() {
	}

	public CalculadoraException(String message) {
		super(message);
	}

	public CalculadoraException(Throwable cause) {
		super(cause);
	}

	public CalculadoraException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalculadoraException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
