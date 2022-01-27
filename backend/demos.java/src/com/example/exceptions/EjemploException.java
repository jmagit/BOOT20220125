package com.example.exceptions;

@SuppressWarnings("serial")
public class EjemploException extends Exception {

	public EjemploException(String message) {
		super(message);
	}

	public EjemploException(String message, Throwable cause) {
		super(message, cause);
	}

	public EjemploException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
