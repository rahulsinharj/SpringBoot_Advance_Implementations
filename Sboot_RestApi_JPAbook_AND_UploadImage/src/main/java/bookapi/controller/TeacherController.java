package bookapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bookapi.entity.Teacher;
import bookapi.service.TeacherService_withoutDB;

@RestController
@RequestMapping("teacher") 												// Doing mapping on class level
public class TeacherController {

	@Autowired
	private TeacherService_withoutDB teacherService;

	// Converting Entity Objects into JSON String :
	public void convertEntityToJson() 
	{
		Teacher teacher1 = new Teacher(181, "Nirbhay Kr Sinha", "Business");
		String jsonString = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			jsonString = objectMapper.writeValueAsString(teacher1);				// new ObjectMapper().writeValueAsString(); is used to convert/serialize a Object into JSON String.					
			System.out.println(jsonString);
			
			Teacher readValue = objectMapper.readValue(jsonString, Teacher.class);	// new ObjectMapper().readValue();	 is used to convert/deserialize a JSON String into Object form.	
			System.out.println(readValue);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET) 		// Handler for - Getting a Teacher obj
	public ResponseEntity<Teacher> getOneTeacher() {
		convertEntityToJson();
		return new ResponseEntity<>(new Teacher(181, "Nirbhay Kr Sinha", "Business"),HttpStatus.OK);
	}
	
	
/*   [GET MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/   
	   	
/*	@GetMapping("/") 													// Handler for - Getting All Teachers
	public List<Teacher> getTeachers() 
	{
		return this.teacherService.getAllTeachers();
	}
*/

//	@GetMapping(path =  "/", produces = "application/xml") 			// After adding "xml" dependency, Now here after restricting to xml format, now Nothing is required to add in Postman Header. 										
	@GetMapping("/") 												// After adding "xml" dependency, Now here api will show the data as per the "Accept" header key in Postman = {application/json , application/xml }
	public ResponseEntity<List<Teacher>> getTeachers() 				// Handler for - Getting All Teachers
	{
		return new ResponseEntity<>(this.teacherService.getAllTeachers(),HttpStatus.ACCEPTED);
	}

/*	
 	// Produces JSON in Response (bydefault) ::		
  	@GetMapping("/{tid}") 												// Handler for - Getting Single Teacher
	public Teacher getSingleTeacher(@PathVariable("tid") int id) 
	{
		Teacher t = this.teacherService.getSingleTeacherById(id);
		System.out.println(t);
		return t;
	}
*/
	
	// Produces XML in Response ::							// Add Maven Dependency :: jackson-dataformat-xml
	@GetMapping(value = "/{tid}", 										
			  	//produces = MediaType.APPLICATION_XML_VALUE)				// After adding "xml" dependency, Now here after only allowing XML, It will only strictly produce XML format data
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})		// After adding "xml" dependency, And since we have allowed XML & JSON both here. So now it will give data according to Header "Accept" = application/xml or application/json in Postman   
	public ResponseEntity<?> getSingleTeacher(@PathVariable("tid") int id) 
	{
		Teacher t = this.teacherService.getSingleTeacherById(id);
		System.out.println("Teacher : "+t);
		if(t!=null)
			return ResponseEntity.ok(t);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Record found, Try again !");
	}

																		// import org.springframework.http.MediaType
/*	// Produces & Consumes - XML & JSON Response both ::				// Add Maven Dependency :: jackson-dataformat-xml
	@GetMapping(value = "/{tid}", 
			  	consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} , 		// Set Content-Type = application/xml , Accept = application/xml
			  	produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} )		// import org.springframework.http.MediaType
	public Teacher getSingleTeacher(@PathVariable("tid") int id) 
	{
		Teacher t = this.teacherService.getSingleTeacherById(id);
		System.out.println(t);
		return t;
	}
*/
	
	
/*   [POST MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/   
	   	
	@PostMapping("/") 													// Handler for - Creating new Book
	public Teacher addTeacher(@RequestBody Teacher teacher) 			// @RequestBody - same way @RequestParam
	{
		Teacher t = this.teacherService.saveTeacher(teacher);
		System.out.println(t);
		return t;
	}

	
/*   [DELETE MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/   
	   	
	@DeleteMapping("/{tid}")												// Handler for - deleting single Teacher
	public List<Teacher> deleteTeacher(@PathVariable("tid") int teacherId) 
	{
		return this.teacherService.deleteTeacherById(teacherId);
	}

	
/*   [PUT MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/   
	   
	@PutMapping("/{tid}") 													// Handler for - Updating existing Teacher
	public List<Teacher> updateTeacher(@PathVariable("tid") int teacherId, @RequestBody Teacher teacher) {
		return this.teacherService.updateTeacher(teacherId, teacher);
	}

}
