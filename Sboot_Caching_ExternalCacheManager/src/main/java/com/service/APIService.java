package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
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

	@CacheEvict(value = "twenty-second-cache", key = "'StudentInCache'+#studentId" , 
				condition = "#isCacheable == null && !#isCacheable")					
	@Cacheable(value = "twenty-second-cache", key = "'StudentInCache'+#studentId" , 
				condition = "#isCacheable != null && #isCacheable")					
	public Student fetchStudent(String studentId, boolean isCacheable) throws InterruptedException 
	{
		Thread.sleep(4000);			// kayde se har baar to 4 sec fetching time lena chaiye n, but aisa hogi nhi each time,
									// kewal 1st time hi 4 sec lagega fetch me, agli baar se cache me stored wahi data aapko direct turant server kar diya jayega.

		return studentList.stream().filter(t -> t.getId().equalsIgnoreCase(studentId)).findFirst().orElse(null);
	}

	
}

/*	@Cacheable 	: to set our cache	=>	@Cacheable make sure that the request comes-In & the responses goes-Out  is cached in the Caching memory. And when the net get operation happens in the application then the data is retrieved from the Cache and not from the Database. 
 * 										If the data is not present at the cache, then it will firstly store the data from Database into cache , and then send the response. 
  
	@CacheEvict : to remove from cache
	@CachePut 	:  to update or cache
	
	@Cacheable(condition = "#isCacheable != null && #isCacheable")   => if "isCacheable" variable comes as true, then only data will be cached. If "isCacheable" comes false then data will not be cached. 
	
	@CacheEvict(condition = "#isCacheable == null && !#isCacheable") => the condition is when "isCacheable" boolean variable ==null, or ==false , then the cache will be evicted, that is it will be removed, and then the actual method will be accessed to get the data. 
	
	beforeInvocation == true  	=>  means before the invocation of this particular method fetchStudent() , all the conditions will be executed first, and then the cache will be evicted. 
	
*/

