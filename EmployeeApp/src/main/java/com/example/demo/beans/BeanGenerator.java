package com.example.demo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BeanGenerator {
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	

}
