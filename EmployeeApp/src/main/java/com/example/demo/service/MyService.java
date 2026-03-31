package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constants.OperationType;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.GetEmployeeDto;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.OperationPayload;
import com.example.demo.feignclient.EmployeeClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyService {
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@Autowired
	private ObjectMapper objectMapper;

	public String login(GetEmployeeDto employeeDto) {

		try {
			OperationPayload<GetEmployeeDto> payload = new OperationPayload<>(OperationType.LOG_In, employeeDto);
			ResponseEntity<?> response = employeeClient.login(payload);
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				LoginResponse loginResponse = objectMapper.convertValue(response.getBody(), LoginResponse.class);
				return loginResponse.token();
			}
		} catch (Exception e) {
			System.out.println("Login Failed: " + e.getMessage());
		}
		return "INVALID";
	}
	
	public String signup(EmployeeDto employeeDto) {

		try {
			OperationPayload<EmployeeDto> payload = new OperationPayload<>(OperationType.SIGN_Up, employeeDto);
			ResponseEntity<?> response = employeeClient.signUp(payload);
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Boolean result = (Boolean) response.getBody();
				if (Boolean.TRUE.equals(result)) {
					return "success";
				}
			}
		} catch (Exception e) {
			System.out.println("Signup Failed: " + e.getMessage());
		}
		return "FAILED";
	}

}
