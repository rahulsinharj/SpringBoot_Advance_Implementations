package bookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class SbootRestApiJpaBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootRestApiJpaBookApplication.class, args);
		
		System.out.println("SBOOT with JPA Book & Upload image !");
	}

}

/*	After adding dependency into pom.xml ::
	
		<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui -->
			<dependency>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-ui</artifactId>
				<version>1.6.6</version>
			</dependency>
	
	And after Enabling Swagger by "@OpenAPIDefinition" annotation in this class,	
		
	We can now Access SwaggerUI from frontend browser with this link :
	 		http://localhost:8080/swagger-ui/index.html
	 		http://localhost:8080/v3/api-docs
	 		
   ---------- 		
	We can also provide description to our APIs, by mentioning below annotations, in controller class ::
	
		@Operation(summary = "This is to fetch all the books stored in Db")			
	    @ApiResponses(value = {
	    		@ApiResponse(responseCode = "200",
				    		description = "Fetched all books from Db" ,
				    		content = {@Content(mediaType = "application/json")}) ,
	    		@ApiResponse(responseCode = "404",
	    					description = "Record not available" ,
	    					content = @Content)
	    })
		@GetMapping("/")
	 		
*/