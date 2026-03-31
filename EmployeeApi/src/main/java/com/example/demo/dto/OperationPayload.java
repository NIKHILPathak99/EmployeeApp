package com.example.demo.dto;

import com.example.demo.constants.OperationType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Generic Data Transfer Object (DTO) used to wrap request and response payloads.
 * 
 * <p>This record encapsulates:
 * <ul>
 *     <li>The type of operation to be performed ({@link OperationType})</li>
 *     <li>The actual data associated with the operation</li>
 * </ul>
 * </p>
 * 
 * <p>It enables a flexible and extensible way to handle multiple operations
 * using a common structure across the application.</p>
 *
 * @param <T>  the type of data carried in the payload
 * @param type the operation type indicating the action to be performed
 * @param data the actual request or response data
 * 
 * @author Nikhil Pathak
 */
public record OperationPayload<T>(@NotNull(message = "OperationType must be needed") OperationType type, @Valid @NotNull(message="Data cannot be null") T data ) {

}
