package com.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ImageUploadService {

	public ResponseEntity<?> uploadFile(String uploadedImagePath, MultipartFile multipartFile)
	{
		String imgName = multipartFile.getOriginalFilename();
		System.out.println("uploadedImagePath : "+uploadedImagePath);
		try {
						
			// Create folder if not created
			File f = new File(uploadedImagePath);
			if (!f.exists()) {
				f.mkdir();
			}	
			
			String imageFileFullPath = uploadedImagePath + File.separator + imgName;
			
			Files.copy(multipartFile.getInputStream(), Paths.get(imageFileFullPath) , StandardCopyOption.REPLACE_EXISTING);
			System.out.println(imgName + " image uploaded successfully at location "+Paths.get(imageFileFullPath).toAbsolutePath());
			
			// Showing imageURL to User during upload ::
			String imgUriString = ServletUriComponentsBuilder.fromCurrentContextPath().path(uploadedImagePath).path(imgName).toUriString();
			System.out.println("User can see their Uploaded Image here  : "+imgUriString);
			
			return ResponseEntity.ok(imgUriString);
			
			// Directly showing image to User during upload ::
//			return ResponseEntity.ok()
//				               	 .contentType(MediaType.IMAGE_JPEG)
//				               	 .body(new InputStreamResource(multipartFile.getInputStream())); 
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in uploading ! Try again later !");
	}
	
	
/*	public InputStream getResource(String uploadedImagePath, String imgName) throws FileNotFoundException
	{
		String imageFileFullPath = uploadedImagePath + File.separator + imgName;
		InputStream iStream = new FileInputStream(imageFileFullPath);
		
		return iStream;
	}
*/	
	
	public Stream<Path> loadAllImg(String uploadedImagePath) 
	{
		try 
		{
			Path root = Paths.get(uploadedImagePath);
		
			return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
		} 
		catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
	
	

}
