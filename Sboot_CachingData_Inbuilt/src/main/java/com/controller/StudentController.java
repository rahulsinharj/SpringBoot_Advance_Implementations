package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Student;
import com.service.APIService;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private APIService aPIService;
	
	@GetMapping("/")
	public ResponseEntity<Student> fetchStudent(@RequestParam(name = "studentId") String studentId) throws InterruptedException 
	{
		return new ResponseEntity<>(aPIService.fetchStudent(studentId), HttpStatus.OK);
	}
	
	
	
}
