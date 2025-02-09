package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dao.EmployeeRepository;
import com.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String URL = "https://gorest.co.in/public/v2/users";
	
/*	public Employee[] consumeApi()
	{
		Employee[] emps = this.restTemplate.getForObject(URL, Employee[].class);
		return emps;
	}
*/
	
	public List<Employee> consumeApi()
	{
		List<Employee> emps = Arrays.asList(this.restTemplate.getForObject(URL, Employee[].class));
		this.employeeRepository.saveAll(emps);			// Also saving this List of Employee into DB
		return emps;
	}
	
	
	
}
