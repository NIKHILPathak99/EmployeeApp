package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
		@Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
		String empMail, 
		
		@NotBlank(message = "Name is required")
		String empName, 
		
        @NotBlank(message = "Contact is required")
        @Size(min = 10, max = 10, message = "Contact must be 10 digits")
		@Pattern(regexp = "^[0-9]+$", message = "Contact must contain only numbers")
		String contact,
		
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
		String password) {

}
