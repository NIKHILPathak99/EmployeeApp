package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) for Employee information.
 * 
 * <p>This record is used to transfer employee-related data between
 * different layers of the application such as controller and service.</p>
 * 
 * <p>It contains basic employee details like email, name, contact,
 * and password.</p>
 * 
 * <p>Records provide an immutable and concise way to define DTOs in Java.</p>
 * 
 * @param empMail  employee email address
 * @param empName  employee name
 * @param contact  employee contact number
 * @param password employee password
 * 
 * @author Nikhil Pathak
 */
public record EmployeeDto(
		String empMail, 
		
		String empName, 
		
		String contact,
		
		String password) {

}
