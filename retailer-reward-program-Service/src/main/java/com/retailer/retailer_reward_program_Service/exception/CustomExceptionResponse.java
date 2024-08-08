package com.retailer.retailer_reward_program_Service.exception;

public class CustomExceptionResponse {
	
		private String errorMessage;
		private String requestedURI;
		
		public CustomExceptionResponse() {}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(final String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getRequestedURI() {
			return requestedURI;
		}

		public void callerURL(final String requestedURI) {
			this.requestedURI = requestedURI;
		}
	

}
