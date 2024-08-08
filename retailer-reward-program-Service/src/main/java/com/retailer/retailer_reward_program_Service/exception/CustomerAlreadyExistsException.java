package com.retailer.retailer_reward_program_Service.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	private String message;

    public CustomerAlreadyExistsException() {}

    public CustomerAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }

	public String getMessage() {
		return message;
	}

	
}
