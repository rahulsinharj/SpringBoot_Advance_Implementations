package com.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.User;
import com.jwt.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// Fetching User from DB ::
		final User user = this.userRepository.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found !");
		}
		else {
			return new CustomUserDetails(user);
		}
			
	 // Without DB ::	
	 /*	if(username.equals("rahul")) {
			return new User("rahul", "rahul123", new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Found !");
		} 
	 */
		
	}
	
	
}
