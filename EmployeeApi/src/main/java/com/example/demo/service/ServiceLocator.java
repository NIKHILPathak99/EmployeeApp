package com.example.demo.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.constants.OperationType;

/**
 * Service Locator class responsible for resolving the appropriate
 * {@link Service} implementation based on {@link OperationType}.
 * 
 * <p>This class follows a strategy-based design pattern where each
 * {@link Service} implementation handles a specific operation type.
 * All service implementations are injected by Spring and registered
 * in a map for quick lookup.</p>
 * 
 * <p>An {@link EnumMap} is used for efficient performance when working
 * with enum keys.</p>
 * 
 * <p>This allows dynamic routing of requests without using multiple
 * conditional statements (if-else or switch).</p>
 * 
 * @author Nikhil Pathak
 */

@Component
public class ServiceLocator {
	
	private final Map<OperationType, Service> serviceMap = new EnumMap<>(OperationType.class);
	
    /**
     * Constructor that initializes the service map with all available
     * Service implementations.
     *
     * @param services list of all Spring-managed Service beans
     */
	public ServiceLocator(List<Service> services) {
		for(Service service: services) {
			serviceMap.put(service.getOperationType(), service);
		}
	}
	
    /**
     * Returns the appropriate Service implementation based on operation type.
     *
     * @param type operation type
     * @return corresponding Service implementation or null if not found
     */
	public Service getProcessor(OperationType type) {
		return serviceMap.get(type);
	}

}
