package com.example.demo.constants;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Enum representing standardized error codes used across the application.
 * 
 * <p>This enum encapsulates:
 * <ul>
 *     <li>Custom error code</li>
 *     <li>Error message</li>
 *     <li>Associated HTTP status</li>
 * </ul>
 * </p>
 * 
 * <p>It helps in maintaining consistency in error handling and response structure.</p>
 * 
 * @author Nikhil Pathak
 */

public enum ErrorCode {
	
	Bad_Request("400", "Invalid Input : ", HttpStatus.BAD_REQUEST),
	
	Data_Not_Found("404", "Not Found : ", HttpStatus.NOT_FOUND),
	
	Credential_Mismatch("401", "Credential Mismatch ", HttpStatus.UNAUTHORIZED),
	
	Access_Denied("403","UnAuthorized Access", HttpStatus.FORBIDDEN),
	
	Data_Confilct("409", "Data Conflict : ", HttpStatus.CONFLICT);
	
	

    /**
     * Custom error code.
     */
    @Getter
    private String errorCode;
    
    /**
     * Error message description.
     */
    @Getter
    private String errorMessage;
    
    /**
     * Associated HTTP status for the error.
     */
    @Getter
    private HttpStatus httpStatus;
	
    /**
     * Constructor to initialize error code details.
     *
     * @param errorCode custom error code
     * @param errorMessage descriptive error message
     * @param httpStatus corresponding HTTP status
     */
	
	ErrorCode(String errorCode, String errorMessage, HttpStatus httpStatus){
		this.errorCode= errorCode;
		
		this.errorMessage=errorMessage;
		
		this.httpStatus = httpStatus;
	}
}
