package com.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jwt.model.User;
import com.jwt.repo.UserRepository;

@SpringBootApplication
public class SbootJwtTokenAuthWithDbApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		String name = "ramesh";
//		User user = new User();
//		user.setUsername(name);
//		user.setPassword(name+"123");
//		user.setEmail(name+"@gmail.com");
//		user.setRole("ROLE_ADMIN");
//		user.setEnabled(true);
//		
//		this.userRepository.save(user);
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SbootJwtTokenAuthWithDbApplication.class, args);
		
		System.out.println("JWT Auth with Database !");
	}
	
	

}
