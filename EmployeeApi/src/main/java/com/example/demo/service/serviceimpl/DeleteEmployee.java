package com.example.demo.service.serviceimpl;

import org.springframework.stereotype.Component;

import com.example.demo.constants.ErrorCode;
import com.example.demo.constants.OperationType;
import com.example.demo.customexception.ApplicationException;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.GetEmployeeDto;
import com.example.demo.dto.OperationPayload;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.service.AbstractService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for deleting an Employee.
 * 
 * <p>This class handles the DELETE_EMPLOYEE operation. It retrieves the
 * employee based on email and deletes the corresponding record from the database.</p>
 * 
 * <p>If the employee does not exist, an {@link ApplicationException} is thrown
 * with {@link ErrorCode#Data_Not_Found}.</p>
 * 
 * <p>This service is managed by Spring and participates in a transactional context.</p>
 * 
 * @author Nikhil Pathak
 */

@Component
@Slf4j
@Transactional
@AllArgsConstructor
public class DeleteEmployee extends AbstractService {

	private ObjectMapper objectMapper;
	
	private EmployeeRepo empRepo;
	
    /**
     * Returns the operation type handled by this service.
     *
     * @return {@link OperationType#DELETE_EMPLOYEE}
     */

	@Override
	public OperationType getOperationType() {
		return OperationType.DELETE_EMPLOYEE;
	}
	
    /**
     * Processes the delete employee request.
     *
     * <p>Converts the payload data into {@link EmployeeDto}, checks if the employee
     * exists using email, and deletes the record if found.</p>
     *
     * @param operationPayload request payload containing employee data
     * @return true if deletion is successful
     * @throws ApplicationException if employee does not exist
     */
	
	@Override
	public boolean processData(OperationPayload<?> operationPayload) throws ApplicationException {
		
		GetEmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), GetEmployeeDto.class);
		
		Employee existingEmp = empRepo.findByEmpMail(empDto.empMail());
		
		 if (empRepo.findByEmpMail(empDto.empMail()) == null) {
			 
			    log.error("Employee doesn't exists with email: " + empDto.empMail());
		        throw new ApplicationException("Employee doesn't exists with email: " + empDto.empMail(), ErrorCode.Data_Not_Found);
		    }
		 
		 empRepo.delete(existingEmp);

		    return true;
		 
	}
}
