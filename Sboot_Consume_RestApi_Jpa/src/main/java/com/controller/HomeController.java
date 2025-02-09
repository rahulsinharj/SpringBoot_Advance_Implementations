package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.EmployeeService;

@RestController
public class HomeController {

	@Autowired
	private EmployeeService employeeService;
	
	// Returning Array of Employee ::
/*	@GetMapping("/emp")
	public ResponseEntity<Employee[]> consumeExternalApi()							
	{
		return new ResponseEntity<>(employeeService.consumeApi(), HttpStatus.OK);
	}
*/
	
	// Returning LIST of Employee ::
	@GetMapping("/emp")												
	public ResponseEntity<?> consumeExternalApi()					// Since we are sending List<Employee> in try bock, and we also have sent error message as string in exception block, 
	{																// So we have to set the return type of this methods as a generic one<?>, otherwise without catch block we could have used ResponseEntity<List<Employee>>  
		try {
			return new ResponseEntity<>(employeeService.consumeApi(), HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in consuming external API");
		}
	}
	
	
}
