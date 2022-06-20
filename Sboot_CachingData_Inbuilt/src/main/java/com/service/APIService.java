package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entity.Address;
import com.entity.Major;
import com.entity.Student;

@Service
public class APIService {

	static List<Student> studentList = new ArrayList<>();

	static {
		studentList.add(new Student("19VC21", "John", "Wayne", "11",
				new Address("12A", "Bay Avenue", "SanFrancisco", "CA", "91234"), Major.MATHS));
		studentList.add(new Student("19VC22", "Mary", "Jane", "11",
				new Address("10A", "Cross Avenue", "SanFrancisco", "CA", "91238"), Major.CHEMISTRY));
		studentList.add(new Student("19VC23", "Peter", "Parker", "11",
				new Address("1A", "First Avenue", "SanFrancisco", "CA", "91934"), Major.PHYSICS));
	}

	@Cacheable(value = "student-cache", key = "'StudentCache'+#studentId")					
	public Optional<Student> fetchStudent(String studentId) throws InterruptedException 
	{
		Thread.sleep(4000);

		return studentList.stream().filter(t -> t.getId().equalsIgnoreCase(studentId)).findFirst();
	}

	
}

/*	@Cacheable : 
	

*/