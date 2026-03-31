package com.example.demo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration class responsible for defining global beans used across the application.
 * 
 * <p>This class provides a centralized way to register commonly used beans,
 * such as {@link ObjectMapper}, so they can be injected wherever required.</p>
 * 
 * <p>The {@link ObjectMapper} bean is used for JSON serialization and
 * deserialization throughout the application.</p>
 * 
 * @author Nikhil Pathak
 */

@Component
public class GlobalBeanGenerator {
	
    /**
     * Creates and registers a singleton {@link ObjectMapper} bean in the Spring context.
     * 
     * <p>This bean can be autowired anywhere in the application to perform
     * JSON parsing and mapping operations.</p>
     * 
     * @return a new instance of {@link ObjectMapper}
     */

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
}
