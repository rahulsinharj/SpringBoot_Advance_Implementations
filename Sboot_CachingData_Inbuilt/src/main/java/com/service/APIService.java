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
public class APIService 
{
	static List<Student> studentList = new ArrayList<>();

	static {
		studentList.add(new Student("STU101", "John", "Wayne", "11", new Address("12A", "Bay Avenue", "SanFrancisco", "CA", "91234"), Major.MATHS));
		studentList.add(new Student("STU102", "Mary", "Jane", "11",  new Address("10A", "Cross Avenue", "SanFrancisco", "CA", "91238"), Major.CHEMISTRY));
		studentList.add(new Student("STU103", "Peter", "Parker", "11", new Address("1A", "First Avenue", "SanFrancisco", "CA", "91934"), Major.PHYSICS));
	}

	@Cacheable(value = "student-cache", key = "'StudentCache'+#studentId")					
	public Optional<Student> fetchStudent(String studentId) throws InterruptedException 
	{
		Thread.sleep(4000);			// kayde se har baar to 4 sec fetching time lena chaiye n, but aisa hogi nhi each time,
									// kewal 1st time hi 4 sec lagega fetch me, agli baar se cache me stored wahi data aapko direct turant server kar diya jayega.

		return studentList.stream().filter(t -> t.getId().equalsIgnoreCase(studentId)).findFirst();
	}

	
}

/*	@Cacheable 	: to set our cache
	@CacheEvict : to remove from cache
	@CachePut 	:  to update or cache
	
*/



