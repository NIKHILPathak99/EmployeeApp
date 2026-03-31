package com.example.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.constants.ErrorCode;
import com.example.demo.dto.ErrorResponse;

/**
 * Global exception handler for handling application-wide exceptions.
 * 
 * <p>This class uses {@link RestControllerAdvice} to intercept exceptions
 * thrown from controller layers and return standardized error responses.</p>
 * 
 * <p>It ensures consistent error handling across the application by mapping
 * specific exceptions to meaningful HTTP responses.</p>
 * 
 * <p>Handled Exceptions:
 * <ul>
 *     <li>{@link MethodArgumentNotValidException} - triggered when validation fails</li>
 *     <li>{@link org.springframework.web.method.annotation.MethodArgumentTypeMismatchException}
 *         - triggered when request parameter type conversion fails</li>
 * </ul>
 * </p>
 * 
 * @author Nikhil Pathak
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
    /**
     * Handles {@link MethodArgumentNotValidException} thrown when
     * a request body fails validation constraints.
     *
     * @param ex the exception thrown by Spring during validation
     * @return {@link ResponseEntity} containing an {@link ErrorResponse}
     *         with error code, message, and HTTP 400 status
     */
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String fistrFieldError = ex.getBindingResult().getFieldError().getDefaultMessage();
		String message = ErrorCode.Bad_Request.getErrorMessage()+fistrFieldError;
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.Bad_Request.getErrorCode(), message);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
    /**
     * Handles {@link org.springframework.web.method.annotation.MethodArgumentTypeMismatchException}
     * thrown when request parameters or path variables cannot be converted
     * to the expected data type.
     *
     * <p>Returns a standardized error response indicating invalid input format.</p>
     *
     * @param ex the exception thrown due to type mismatch
     * @return {@link ResponseEntity} containing error details with HTTP 400 status
     */
	
	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> MethodArgumentTypeMismatchException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
//		String fistrFieldError = ex.getBindingResult().getFieldError().getDefaultMessage();
		String fistrFieldError = ex.getMessage();
		String message = ErrorCode.Bad_Request.getErrorMessage()+fistrFieldError;
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.Bad_Request.getErrorCode(), message);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}

}
