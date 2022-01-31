package com.example.juegos;

@SuppressWarnings("serial")
public class JuegoException extends Exception {

	public JuegoException(String message) {
		super(message);
	}

	public JuegoException(String message, Throwable cause) {
		super(message, cause);
	}

	public JuegoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
