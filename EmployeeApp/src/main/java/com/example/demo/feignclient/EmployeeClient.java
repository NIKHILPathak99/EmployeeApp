package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.GetEmployeeDto;
import com.example.demo.dto.OperationPayload;


@FeignClient(name = "employeeapi")
public interface EmployeeClient {

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody OperationPayload<GetEmployeeDto> payload);

    @GetMapping("/getemployee")
    ResponseEntity<?>  getEmployee(@RequestBody OperationPayload<GetEmployeeDto> payload);

    @PostMapping("/signup")
    ResponseEntity<?>  signUp(@RequestBody OperationPayload<EmployeeDto> payload);

    @PutMapping("/updateemployee")
    ResponseEntity<?>  updateEmployee(@RequestBody OperationPayload<EmployeeDto> payload);

    @DeleteMapping("/deleteemployee")
    ResponseEntity<?>  deleteEmployee(@RequestBody OperationPayload<GetEmployeeDto> payload);
}
