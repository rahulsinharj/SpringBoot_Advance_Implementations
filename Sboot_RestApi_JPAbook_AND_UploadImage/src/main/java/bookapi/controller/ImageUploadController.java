package bookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bookapi.payload.FileResponse;
import bookapi.uploadService.ImageUploadService;

@RestController
@RequestMapping("file")									
public class ImageUploadController {

	@Autowired
	private ImageUploadService imageUploadService;
	
	@Value("${projectPath.uploadedImage}")
	private String uploadPath; 							// 	images\
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUploader(@RequestParam("image") MultipartFile imgfile)
	{
		try {
			if(imgfile.isEmpty()) {
				return new ResponseEntity<>(new FileResponse(imgfile.getOriginalFilename(), "Request must contain file !") , HttpStatus.BAD_REQUEST);
			}
			
			System.out.println("File size : " +imgfile.getSize()/1024 +" kb");	
			if(imgfile.getSize() > (10*1048576)) {
				return new ResponseEntity<>(new FileResponse(imgfile.getOriginalFilename(), "Image Size should be less than 10MB !") , HttpStatus.BAD_REQUEST);
			}
			
			if(!imgfile.getContentType().equals("image/jpeg")) {
				return new ResponseEntity<>(new FileResponse(imgfile.getOriginalFilename(), "Only JPEG file content type is allowed") , HttpStatus.BAD_REQUEST);
			}
			
			String fileName = this.imageUploadService.uploadImage(this.uploadPath, imgfile);
			
			//	Also - we can Save file into DB Repository 
			
			return new ResponseEntity<>(new FileResponse(fileName, "Image Sucessfully Uploaded !") , HttpStatus.OK);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(imgfile.getOriginalFilename(), "Image not uploaded due to some error on server !") , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			
	}
}
