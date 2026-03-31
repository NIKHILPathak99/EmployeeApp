package com.example.demo.constants;

/**
 * Enum representing different types of operations that can be performed
 * on Employee entities within the application.
 * 
 * <p>This is typically used to identify the type of action being executed,
 * such as saving, retrieving, updating, or deleting employee data.</p>
 * 
 * <p>Using an enum ensures type safety and avoids the use of hardcoded strings.</p>
 * 
 * @author Nikhil Pathak
 */
public enum OperationType {

    /**
     * Operation for saving a new employee.
     */
    SIGN_Up,
    
    /**
     * Operation for login an employee.
     */
    LOG_In,
    
    /**
     * Operation for fetching employee details.
     */
    GET_EMPLOYEE,
    
    /**
     * Operation for updating existing employee data.
     */
    UPDATE_EMPLOYEE,
    
    /**
     * Operation for deleting an employee.
     */
    DELETE_EMPLOYEE;
}
