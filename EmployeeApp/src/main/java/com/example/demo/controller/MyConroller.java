package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.GetEmployeeDto;
import com.example.demo.service.MyService;

import jakarta.servlet.http.HttpSession;



@Controller
public class MyConroller {
	
	@Autowired 
	private MyService service;

	@GetMapping("/")
	public String getMethodName() {
		return "index";
	}
	
	@GetMapping("/signuppage")
	public String getSignUp() {
		return "signup";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
	    String user = (String) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/";
	    }
	    return "dashboard";
	}
	
	@PostMapping("/login")
	public String postMethodName(@RequestParam String empMail,  @RequestParam String password, Model model, HttpSession session ) {
		GetEmployeeDto employeeDto = new GetEmployeeDto(empMail, password);
		String result = service.login(employeeDto);
		if ("INVALID".equals(result)) {
			model.addAttribute("error", "Invalid Credentials");
			return "index";
		}
		session.setAttribute("token", result);
		return "dashboard";
	}
	
	@PostMapping("/signup")
	public String signup(@RequestParam String empName, @RequestParam String empMail, @RequestParam String password, @RequestParam String contact, Model model) {
		EmployeeDto dto = new EmployeeDto(empMail, empName, contact, password);
		String result = service.signup(dto);
		if ("success".equals(result)) {
			model.addAttribute("success", "Signup Successful! Please login.");
			return "index"; 
		}
		model.addAttribute("error", "Signup Failed");
		return "signup";
	}
	
	
	
}
