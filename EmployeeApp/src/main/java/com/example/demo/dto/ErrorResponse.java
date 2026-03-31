package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) for representing error responses.
 * 
 * <p>This record is used to send standardized error information
 * from the server to the client whenever an exception occurs.</p>
 * 
 * <p>It contains a custom error code and a descriptive error message.</p>
 * 
 * <p>Using a consistent error response structure improves API usability
 * and makes error handling easier on the client side.</p>
 * 
 * @param errorCode    custom error code representing the type of error
 * @param errorMessage descriptive message explaining the error
 * 
 * @author Nikhil Pathak
 */
public record ErrorResponse(String errorCode, String errorMessage) {

}
