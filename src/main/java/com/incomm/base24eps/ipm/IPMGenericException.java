package com.incomm.base24eps.ipm;

public class IPMGenericException extends Exception {

	public IPMGenericException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public IPMGenericException(String msg) {
		super(msg);
	}

	public IPMGenericException() {
	}
	public String getMessage() {
		return super.getMessage();
	}

}

