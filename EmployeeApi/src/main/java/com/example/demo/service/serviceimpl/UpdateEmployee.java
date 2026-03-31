package com.example.demo.service.serviceimpl;

import java.math.BigInteger;

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
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for updating an existing Employee.
 * 
 * <p>This class handles the UPDATE_EMPLOYEE operation. It validates
 * whether the employee exists based on email and updates the
 * corresponding fields.</p>
 * 
 * <p>If the employee does not exist, an {@link ApplicationException}
 * is thrown with {@link ErrorCode#Data_Not_Found}.</p>
 * 
 * <p>This service executes within a transactional context.</p>
 * 
 * @author Nikhil Pathak
 */
@Component
@Transactional
@AllArgsConstructor
@Slf4j
public class UpdateEmployee extends AbstractService {
	
	private ObjectMapper objectMapper;
	
	private EmployeeRepo empRepo;

    /**
     * Returns the operation type handled by this service.
     *
     * @return {@link OperationType#UPDATE_EMPLOYEE}
     */
	@Override
	public OperationType getOperationType() {
		return OperationType.UPDATE_EMPLOYEE;
	}

    /**
     * Processes the request to update an existing employee.
     *
     * <p>Converts payload data into {@link EmployeeDto}, verifies if the
     * employee exists, and updates the entity fields.</p>
     *
     * @param operationPayload request payload containing updated employee data
     * @return true if update is successful
     * @throws ApplicationException if employee does not exist
     */
	@Override
	public boolean processData(OperationPayload<?> operationPayload) throws ApplicationException {
		
		EmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), EmployeeDto.class);
		Employee existingEmp = empRepo.findByEmpMail(empDto.empMail());
		 if (empRepo.findByEmpMail(empDto.empMail()) == null) {
			    log.error("Employee doesn't exists with email: " + empDto.empMail());
		        throw new ApplicationException("Employee doesn't exists with email: " + empDto.empMail(), ErrorCode.Data_Not_Found);
		    }
		    existingEmp.setEmpName(empDto.empName());
		    existingEmp.setContact(new BigInteger(empDto.contact()));
		    existingEmp.setPassword(empDto.password());

		    return true;
	}
	
}
