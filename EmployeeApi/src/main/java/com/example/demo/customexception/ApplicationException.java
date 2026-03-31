package com.example.demo.customexception;

import org.springframework.http.HttpStatus;

import com.example.demo.constants.ErrorCode;

public class ApplicationException extends Exception{

	private ErrorCode errorCode;
	
	private String errorMessage;
	
	public ApplicationException() {
		this.errorCode=null;
	}
	
	public ApplicationException(ErrorCode errorCode) {
		this.errorCode=errorCode;
	}
	
	public ApplicationException(String msg, ErrorCode errorCode) {
		super(msg);
		this.errorCode=errorCode;
		this.errorMessage=msg;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String getErrorCodeMessage() {
		if (errorCode == null) return errorMessage;
		return errorCode.getErrorMessage()+errorMessage;
	}
	
	public String getErrorCodeValue() {
		return errorCode.getErrorCode();
	}
	
	public HttpStatus getHttpStatus() {
		return errorCode.getHttpStatus();
	}
	
	
}
