package com.example.demo.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an Employee in the system.
 * 
 * <p>This class is mapped to a database table using JPA annotations.
 * It contains employee-related details such as name, contact, email,
 * and password.</p>
 * 
 * <p>Lombok annotations are used to reduce boilerplate code by generating
 * getters, setters, constructors, and builder methods automatically.</p>
 * 
 * <p>The {@code empId} is the primary key and is auto-generated.</p>
 * 
 * @author Nikhil Pathak
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	@Column(nullable = false)
	private String empName;
	
	@Column(nullable = false)
	private BigInteger contact;
	
	@Column(nullable = false, unique = true)
	private String empMail;
	
	@Column(nullable = false)
	private String password;
}
