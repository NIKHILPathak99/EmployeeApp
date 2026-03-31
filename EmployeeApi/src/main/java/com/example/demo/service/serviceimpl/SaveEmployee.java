package com.example.demo.service.serviceimpl;

import java.math.BigInteger;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.constants.ErrorCode;
import com.example.demo.constants.OperationType;
import com.example.demo.customexception.ApplicationException;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.OperationPayload;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.service.AbstractService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

/**
 * Service implementation for saving a new Employee.
 * 
 * <p>This class handles the SAVE_EMPLOYEE operation. It validates whether
 * an employee already exists with the given email and, if not, persists
 * a new employee record in the database.</p>
 * 
 * <p>If an employee with the same email already exists, an
 * {@link ApplicationException} is thrown with
 * {@link ErrorCode#Data_Confilct}.</p>
 * 
 * <p>This service runs within a transactional context.</p>
 * 
 * @author Nikhil Pathak
 */
@Component
@AllArgsConstructor
@Transactional 
public class SaveEmployee extends AbstractService{
	
	private ObjectMapper objectMapper;
	
	private EmployeeRepo empRepo;
	
	private PasswordEncoder passwordEncoder;

    /**
     * Returns the operation type handled by this service.
     *
     * @return {@link OperationType#SAVE_EMPLOYEE}
     */
	@Override
	public OperationType getOperationType() {
		return OperationType.SIGN_Up;
	}
	
    /**
     * Processes the request to save a new employee.
     *
     * <p>Converts payload data into {@link EmployeeDto}, checks for duplicate
     * email, and saves the employee if validation passes.</p>
     *
     * @param operationPayload request payload containing employee data
     * @return true if employee is saved successfully
     * @throws ApplicationException if employee already exists
     */
	@Override
	public boolean processData(OperationPayload<?> operationPayload) throws ApplicationException {
		
		  EmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), EmployeeDto.class);
		    if (empRepo.findByEmpMail(empDto.empMail()) != null) {
		        throw new ApplicationException("Employee already exists with email: " + empDto.empMail(), ErrorCode.Data_Confilct);
		    }
		    
		    Employee employee = new Employee();
		    employee.setEmpMail(empDto.empMail());
		    employee.setEmpName(empDto.empName());
		    employee.setContact(new BigInteger(empDto.contact()));
		    employee.setPassword(passwordEncoder.encode(empDto.password()));
		    empRepo.save(employee);
		    return true;
	}

}
