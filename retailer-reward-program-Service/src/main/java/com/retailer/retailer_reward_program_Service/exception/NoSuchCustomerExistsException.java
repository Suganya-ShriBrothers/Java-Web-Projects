package com.retailer.retailer_reward_program_Service.exception;

public class NoSuchCustomerExistsException extends RuntimeException{
	
	private String message;

    public NoSuchCustomerExistsException() {}

    public NoSuchCustomerExistsException(String msg) {
        super(msg);
        this.message = msg;
    }

	public String getMessage() {
		return message;
	}

	
}
