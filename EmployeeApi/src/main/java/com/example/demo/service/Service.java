package com.example.demo.service;

import com.example.demo.constants.OperationType;
import com.example.demo.customexception.ApplicationException;
import com.example.demo.dto.OperationPayload;


/**
 * Core service interface defining contract for all operation-based services.
 * 
 * <p>Each implementation of this interface is responsible for handling
 * a specific {@link OperationType}. This design supports a strategy-based
 * approach where the correct service is selected dynamically.</p>
 * 
 * <p>The interface defines methods for:
 * <ul>
 *     <li>Identifying the supported operation type</li>
 *     <li>Fetching data</li>
 *     <li>Processing data (create, update, delete)</li>
 * </ul>
 * </p>
 * 
 * <p>All implementations are expected to be Spring-managed beans.</p>
 * 
 * @author Nikhil Pathak
 */

@org.springframework.stereotype.Service
public interface Service  {
	
	  /**
     * Returns the operation type supported by this service.
     * 
     * @return the {@link OperationType} handled by this service
     */
	OperationType getOperationType();
	
    /**
     * Retrieves data based on the given operation payload.
     *
     * @param operationPayload request payload containing operation details
     * @return response payload containing requested data
     * @throws ApplicationException in case of business logic failure
     */
	OperationPayload<?> getData( OperationPayload<?> operationPayload) throws ApplicationException;
	
    /**
     * Processes data such as create, update, or delete operations.
     *
     * @param operationPayload request payload containing operation details
     * @return true if operation is successful, otherwise false
     * @throws ApplicationException in case of business logic failure
     */
	boolean processData(OperationPayload<?> operationPayload) throws ApplicationException;
}
