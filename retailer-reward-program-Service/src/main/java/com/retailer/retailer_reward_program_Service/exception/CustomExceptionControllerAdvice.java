package com.retailer.retailer_reward_program_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionControllerAdvice {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody CustomExceptionResponse handleResourceNotFound(final CustomerAlreadyExistsException exception,
			final HttpServletRequest request) {

		CustomExceptionResponse error = new CustomExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}
	
	@ExceptionHandler(NoSuchCustomerExistsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody CustomExceptionResponse handleResourceNotFound(final NoSuchCustomerExistsException exception,
			final HttpServletRequest request) {

		CustomExceptionResponse error = new CustomExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CustomExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {

		CustomExceptionResponse error = new CustomExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}


}
