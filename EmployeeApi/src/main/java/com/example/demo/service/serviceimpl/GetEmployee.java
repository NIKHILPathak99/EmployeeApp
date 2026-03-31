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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for fetching Employee details.
 * 
 * <p>This class handles the GET_EMPLOYEE operation. It retrieves
 * employee information based on the provided email and returns
 * the corresponding data.</p>
 * 
 * <p>If the employee is not found, an {@link ApplicationException}
 * is thrown with {@link ErrorCode#Data_Not_Found}.</p>
 * 
 * <p>The response excludes sensitive information like password.</p>
 * 
 * @author Nikhil Pathak
 */

@Component
@AllArgsConstructor
@Slf4j
public class GetEmployee extends AbstractService {
	
	private EmployeeRepo empRepo;
	
	private ObjectMapper objectMapper;
	
    /**
     * Returns the operation type handled by this service.
     *
     * @return {@link OperationType#GET_EMPLOYEE}
     */

	@Override
	public OperationType getOperationType() {
		return OperationType.GET_EMPLOYEE;
	}
	
    /**
     * Retrieves employee data based on the provided payload.
     *
     * <p>Converts the payload into {@link EmployeeDto}, fetches the employee
     * from the database, and maps it back to a DTO for response.</p>
     *
     * @param operationPayload request payload containing employee email
     * @return {@link OperationPayload} containing employee details
     * @throws ApplicationException if employee is not found
     */
	
	@Override
	public OperationPayload<?> getData(OperationPayload<?> operationPayload) throws ApplicationException {
		
		GetEmployeeDto empDto = objectMapper.convertValue(operationPayload.data(), GetEmployeeDto.class);
		Employee employeeData = empRepo.findByEmpMail(empDto.empMail());
		
	    if (employeeData == null) {
	    	log.warn("Employee not found with email: " + empDto.empMail());
	        throw new ApplicationException("Employee not found with email: " + empDto.empMail(), ErrorCode.Data_Not_Found);
	    }
	    
	    EmployeeDto responseDto = new EmployeeDto(employeeData.getEmpMail(), employeeData.getEmpName(), employeeData.getContact().toString(), null);
	    
	    return new OperationPayload<>(OperationType.GET_EMPLOYEE,responseDto);
	}

}
