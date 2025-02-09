package bookapi.uploadService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {

	public String uploadImage(String uploadPath, MultipartFile imgfile) 
	{
		// File name
		String imgOriginalName = imgfile.getOriginalFilename();			// abc.png

		try {
			// Setting filename with randomId name
			String randomId = UUID.randomUUID().toString();
			String imgNewName = randomId.concat(imgOriginalName.substring(imgOriginalName.lastIndexOf("."))); 
			
			// Full path
			String filePath = uploadPath + File.separator + imgNewName;

			// Create folder if not created
			File f = new File(uploadPath);
			if (!f.exists()) {
				f.mkdir();
			}

			// File copy
			Files.copy(imgfile.getInputStream(), Paths.get(filePath) , StandardCopyOption.REPLACE_EXISTING);
			System.out.println(imgOriginalName + " image uploaded successfully at location "+Paths.get(filePath).toAbsolutePath());
			return imgOriginalName;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return imgOriginalName;
		}
		
/*
 	uploadPath								=	images\
  	
  		//	String filePath = uploadPath + File.separator + imgNewName;
  	
  	Paths.get(filePath)						=	images\63ec626c-f17c-44f9-bdfc-e700a3999456.jpg
  	Paths.get(filePath).toAbsolutePath()	=	E:\Stu\Code Files\GIT Eclipse Files\SpringBoot\Sboot_RestApi_JPAbook_AND_UploadImage\images\d29e0480-9b93-4865-b7f4-b5dc7b8d0761.jpg   
  	  
  	
  
*/
		
		
	}
}
