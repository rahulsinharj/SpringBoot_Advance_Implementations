package bookapi.controller;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bookapi.uploadService.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("myfile") MultipartFile file )
	{
		try {
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
			if(!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG file content type is allowed");
			}
			
			System.out.println("Upload parameter name in FORM : " +file.getName());		// Return the name of the form parameter in the multipart form.
			System.out.println("File content type : " +file.getContentType());			// Return the content type of the file.
			System.out.println("File size : " +file.getSize()/1024 +" kb");				// Return the size of the file in bytes , now in Kb now
			System.out.println("OriginalFilename : " +file.getOriginalFilename());		// Return the original filename in the client's filesystem. 
			
			System.out.println("Current working path : "+ Paths.get("").toAbsolutePath().toString() );
			System.out.println();
			
			// Now file-upload code ::
			boolean result = fileUploadHelper.uploadFile(file);
			if(result) {
				
				// Returning imageFile in URL :
				String imgUriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString();
				System.out.println("User can see their Uploaded Image here  : "+imgUriString);
				
				return 	ResponseEntity.ok(imgUriString);
					// ServletUriComponentsBuilder.fromCurrentContextPath()  => yaha tak we will get  http://localhost:8080/
				
//				return ResponseEntity.ok(file.getOriginalFilename() +" - file is successfully uploaded");
//				return ResponseEntity.status(HttpStatus.OK).body("Uploaded Fine");
//				return ResponseEntity.status(HttpStatus.CREATED).body(file.getOriginalFilename() +" - file is successfully uploaded");
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in uploading ! Try again later !");
		
	}
	 
	
	
}
