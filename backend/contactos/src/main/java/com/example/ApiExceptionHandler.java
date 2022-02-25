package com.example;

import java.io.Serializable;

import javax.annotation.processing.FilerException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {
	public static class ErrorMessage implements Serializable {
		private static final long serialVersionUID = 1L;
		private String error, message;

		public ErrorMessage(String error, String message) {
			this.error = error;
			this.message = message;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	private final Log log = LogFactory.getLog(this.getClass());

	@ExceptionHandler({ NotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		log.error("NotFoundException", exception);
		return new ErrorMessage(exception.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler({ BadRequestException.class, MissingRequestHeaderException.class, FilerException.class,
			InvalidDataException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage badRequest(Exception exception) {
		log.error("BadRequest", exception);
		return new ErrorMessage(exception.getMessage(), "");
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage notValidException(MethodArgumentNotValidException exception) {
		log.error("BadRequest", exception);
		StringBuilder sb = new StringBuilder("ERRORES:");
		exception.getBindingResult().getFieldErrors().stream()
				.forEach(err -> sb.append(" " + err.getField() + ": " + err.getDefaultMessage()));
		return new ErrorMessage("Invalid data", sb.toString());
	}

	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorMessage badFormat(Exception exception) {
		log.error("NotAcceptable", exception);
		return new ErrorMessage("Formato no soportado", "");
	}

}
