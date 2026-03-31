package com.example.demo.service;

import com.example.demo.customexception.ApplicationException;
import com.example.demo.dto.OperationPayload;


/**
 * Abstract base class for all service implementations.
 * 
 * <p>This class provides default implementations for the methods defined
 * in the {@link Service} interface. Subclasses can override these methods
 * to provide specific business logic.</p>
 * 
 * <p>By default:
 * <ul>
 *     <li>{@code getData()} returns null</li>
 *     <li>{@code processData()} returns false</li>
 * </ul>
 * </p>
 * 
 * <p>This class helps in reducing boilerplate code by allowing subclasses
 * to override only the required methods.</p>
 * 
 * @author Nikhil Pathak
 */
public abstract class AbstractService implements Service {

    /**
     * Retrieves data based on the given operation payload.
     * 
     * <p>Default implementation returns null. Subclasses should override
     * this method if data retrieval logic is required.</p>
     *
     * @param operationPayload request payload containing operation details
     * @return operation response payload or null by default
     * @throws ApplicationException in case of business logic failure
     */
	@Override
	public OperationPayload<?> getData(OperationPayload<?> operationPayload) throws ApplicationException {
		return null;
	}
	
	   /**
     * Processes data based on the given operation payload.
     * 
     * <p>Default implementation returns false. Subclasses should override
     * this method to implement create, update, or delete operations.</p>
     *
     * @param operationPayload request payload containing operation details
     * @return true if operation is successful, otherwise false
     * @throws ApplicationException in case of business logic failure
     */
	@Override
	public boolean processData(OperationPayload<?> operationPayload) throws ApplicationException {
		return false;
	}
	
}
