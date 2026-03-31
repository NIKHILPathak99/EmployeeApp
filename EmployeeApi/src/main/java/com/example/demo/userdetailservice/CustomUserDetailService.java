package com.example.demo.userdetailservice;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	  private EmployeeRepo repo;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Employee emp = repo.findByEmpMail(username);
	        if (emp == null) {
	            throw new UsernameNotFoundException("User not found");
	        }

	        return User
	                .withUsername(emp.getEmpMail())
	                .password(emp.getPassword())
	                .roles("USER")
	                .build();
	    }

}
