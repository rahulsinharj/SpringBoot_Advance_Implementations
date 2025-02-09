package com.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.model.FileInfo;
import com.service.ImageUploadService;

@RestController
@RequestMapping("image")
public class ImageUploadController {

	@Value("${uploadedImagePath}")
	private String uploadedImagePath;
	
	@Autowired
	private ImageUploadService imageUploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("myfile") MultipartFile file)
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
			
			
			// Now file-upload code ::
			return imageUploadService.uploadFile(uploadedImagePath, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in uploading ! Try again later !");
		
	}
	
	@GetMapping(value =  "/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> downloadImage(@PathVariable("imageName") String imageName)			// HttpServletResponse servletResponse 
	{
		try {
//			InputStream imgInputStream = this.imageUploadService.getResource(uploadedImagePath, imageName);
			
			InputStream imgInputStream = new FileInputStream(uploadedImagePath + imageName);

//			servletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
//			StreamUtils.copy(imgInputStream, servletResponse.getOutputStream());
			
			System.out.println(imageName +" is getting displayed to Client User !");
		
			return ResponseEntity.ok()
				               	 .contentType(MediaType.IMAGE_JPEG)
				               	 .body(new InputStreamResource(imgInputStream)); 
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in uploading ! Try again later !");
		
			
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getListFiles() 
	{
		List<FileInfo> fileInfos = this.imageUploadService.loadAllImg(uploadedImagePath).map(path -> {
				
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
										.fromMethodName(ImageUploadController.class, "downloadImage", path.getFileName()
										.toString()).build().toString();
				
			return new FileInfo(filename, url);
		    
			}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}
	
/*	
  	[
	    {
	        "name": "apple2.jpg",
	        "url": "http://localhost:8080/image/apple2.jpg"
	    },
	    {
	        "name": "lockandkey.jpeg",
	        "url": "http://localhost:8080/image/lockandkey.jpeg"
	    },
	    {
	        "name": "picture5.jpg",
	        "url": "http://localhost:8080/image/picture5.jpg"
	    }
	]
  
 */
	
	
	
}
