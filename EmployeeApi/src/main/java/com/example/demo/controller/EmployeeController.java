package com.example.demo.controller;

import static com.example.demo.constants.Endpoints.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.ErrorCode;
import com.example.demo.constants.OperationType;
import com.example.demo.customexception.ApplicationException;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.GetEmployeeDto;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.OperationPayload;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.service.ServiceLocator;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * REST Controller for handling Employee-related operations.
 * 
 * <p>This controller exposes endpoints for performing CRUD operations
 * on Employee data. It uses a {@link ServiceLocator} to dynamically
 * resolve the appropriate service based on {@link OperationType}.</p>
 * 
 * <p>All APIs accept a generic {@link OperationPayload} and return either
 * the operation result or a standardized {@link ErrorResponse} in case of failure.</p>
 * 
 * @author Nikhil Pathak
 */

@RestController
@AllArgsConstructor
public class EmployeeController {

	private ServiceLocator service;
	
	private ObjectMapper objectMapper;
	
    private AuthenticationManager authManager;
    
    private JwtUtil jwtUtil;
	
	
    @PostMapping(Log_In)
    public ResponseEntity<?> login(@Valid @RequestBody OperationPayload<GetEmployeeDto> operationPayload) {
    	
    	GetEmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), GetEmployeeDto.class);

        try {
        	Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    		empDto.empMail(), empDto.password()
                    )
            );
        	 return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(empDto.empMail())));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(ErrorCode.Credential_Mismatch.getHttpStatus()).body(new ErrorResponse(ErrorCode.Credential_Mismatch.getErrorCode(), ErrorCode.Credential_Mismatch.getErrorMessage()));
		}
    }
	
    /**
     * Fetches employee details based on the provided payl
     * oad.
     *
     * @param operationPayload request payload containing operation type and data
     * @return employee data wrapped in {@link OperationPayload} or error response
     */
	
	@GetMapping(Get_Employee)
	public ResponseEntity<?> getEmployee(@Valid @RequestBody OperationPayload<GetEmployeeDto> operationPayload) {
		
		try {
			OperationPayload<?> response = service.getProcessor(operationPayload.type()).getData(operationPayload);
			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return ResponseEntity.status(ErrorCode.Data_Not_Found.getHttpStatus()).body(new ErrorResponse(e.getErrorCodeValue(), e.getMessage()));
		}
	}
	
    /**
     * Saves a new employee.
     *
     * @param operationPayload request payload containing employee data
     * @return true if saved successfully, otherwise error response
     */
	
	@PostMapping(Sign_Up)
	public ResponseEntity<?> saveEmployee(@Valid @RequestBody OperationPayload<EmployeeDto> operationPayload) {
		
		try {
			boolean response = service.getProcessor(operationPayload.type()).processData(operationPayload);
			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return ResponseEntity.status(ErrorCode.Data_Not_Found.getHttpStatus()).body(new ErrorResponse(e.getErrorCodeValue(), e.getMessage()));
		}
	}
	
    /**
     * Updates an existing employee.
     *
     * <p>If the update is successful, the updated employee data is fetched
     * and returned in the response.</p>
     *
     * @param operationPayload request payload containing updated employee data
     * @return updated employee data or error response
     */
	
	@PutMapping(Update_Employee)
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody OperationPayload<EmployeeDto> operationPayload) {
		
		try {
			boolean response = service.getProcessor(operationPayload.type()).processData(operationPayload);
			EmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), EmployeeDto.class);
			OperationPayload<?> updatedEmployee=null;
			if(response)
				updatedEmployee = service.getProcessor(OperationType.GET_EMPLOYEE).getData(new OperationPayload<GetEmployeeDto>(OperationType.GET_EMPLOYEE, new GetEmployeeDto(empDto.empMail(), null)));
			return ResponseEntity.ok(updatedEmployee);
		} catch (ApplicationException e) {
			return ResponseEntity.status(ErrorCode.Data_Not_Found.getHttpStatus()).body(new ErrorResponse(e.getErrorCodeValue(), e.getMessage()));
		}
	}
	
    /**
     * Deletes an employee.
     *
     * @param operationPayload request payload containing employee identifier
     * @return true if deletion is successful, otherwise error response
     */
	
	@DeleteMapping(Delete_Employee)
	public ResponseEntity<?> deleteEmployee(@Valid @RequestBody OperationPayload<GetEmployeeDto> operationPayload) {
		
		try {
			boolean response = service.getProcessor(operationPayload.type()).processData(operationPayload);
			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return ResponseEntity.status(ErrorCode.Data_Not_Found.getHttpStatus()).body(new ErrorResponse(e.getErrorCodeValue(), e.getMessage()));
		}
	}
	
	
}
