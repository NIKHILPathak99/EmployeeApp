package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GetEmployeeDto(
		@Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
		String empMail,
		
		
		String password) {

}
