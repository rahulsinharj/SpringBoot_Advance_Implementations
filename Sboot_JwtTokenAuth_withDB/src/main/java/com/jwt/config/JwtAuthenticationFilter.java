package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.service.CustomUserDetailsService;
import com.jwt.util.JwtUtility;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
	{
		System.out.println("Inside Jwt-Authentication-FILTER");
		
		// Get Jwt
		// And it is starting with Bearer ?
		// Then Validate
																			// We'll check whether any "Authorization" information is coming through Request-HEADER. From that we'll be receiving JWT TOKEN String.
		String authorizationHeader = request.getHeader("Authorization");	// check karega ki HEADER me "Authorization" naam ka koi KEY hai aaraha hai kya ? Agar hoga to usme rakha value receive ho jayega. 
		String username = null;
		String jwtToken = null;
		
		// Null And Format check ::
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) 		// Once we receive we AuthorizationHeader
		{
			jwtToken = authorizationHeader.substring(7);					// We'll remove the Bearer part and cutdown the actual token key 
			
			try {
				username = this.jwtUtility.getUsernameFromToken(jwtToken);		// jwtUtility class will validate the "PayLoad Data" having-in that token, by checking its "claims" from the jwt "SignatureData" as per the assigned "Secret Key". If claims are validated, then it will return the validated username coming from that token. 
				System.out.println("Token Username : "+username);
			} 
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				e.printStackTrace();
			} 
			catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
			System.out.println("JWT Token does not begin with Bearer String");
		}
		
		// Once we get the token, validate it from database user.
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);	// Validating the USERNAME by loading/retrieving the USER DETAILS (either from Database)

			// If token is valid, configure Spring Security to manually set authentication.
			if (jwtUtility.validateToken(jwtToken, userDetails)) 
			{
				// Create an Authentication for the validated user 
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            // Now, we'll SET the User-Authentication in the "SecurityContext", with-that we are specifying that the current user is now authenticated. So that it passes the Spring Security Configurations successfully.  
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
        }
		else {
			LOGGER.warn("Token is not validated..");
			System.out.println("Token is not validated..");
        }
		
		filterChain.doFilter(request, response);		// Then we'll do the filter chain, and forward our request.
	}

	
	
}
