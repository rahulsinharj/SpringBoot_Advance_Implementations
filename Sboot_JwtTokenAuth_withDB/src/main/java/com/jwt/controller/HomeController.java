package com.jwt.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

	@RequestMapping("/welcome")
	public String welcome()
	{
		String text = "This is a private page. ";
		text += "Welcome to this page, only allowed to Authenticated users !";
		return text;
 	}
	
	@RequestMapping("/getUser")
	public String getUser(Principal principal)
	{
		String name =  principal.getName();
	//	return "{\"name\":\"Rahul\"}";
		return "{\"name\":\""+name+"\"}";
 	}
	
	
}
