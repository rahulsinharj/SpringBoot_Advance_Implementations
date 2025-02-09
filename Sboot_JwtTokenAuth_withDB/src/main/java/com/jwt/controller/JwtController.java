package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.service.CustomUserDetailsService;
import com.jwt.util.JwtUtility;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth")
	public ResponseEntity<JwtResponse> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception		//  @RequestBody - will deserialize the incoming JSON request data by converting it into a User-Entity-Object. 
	{
		System.out.println("JwtRequest : " +jwtRequest);
		
		try 
		{
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} 
		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Bad Credentials");
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// Once the user credentials Authentication is successful, Now we'll be creating the JWT TOKEN using JwtUtility class  ::
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtility.generateToken(userDetails);
			System.out.println("Token : "+token);
		
		// {"token" : "value"}	
			
		//	return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
		return ResponseEntity.ok(new JwtResponse(token));	
		
	}



}
