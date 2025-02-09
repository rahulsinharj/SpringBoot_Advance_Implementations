package bookapi.uploadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
//	public final String UPLOAD_PKG = Paths.get("").toAbsolutePath().toString();
//	public final String UPLOAD_DIR = UPLOAD_PKG +"\\src\\main\\resources\\static\\image";

	public final String UPLOAD_DIR = Paths.get("src/main/resources/static/image").toAbsolutePath().toString();
	
	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean b = false;		// By default it is not uploaded
		System.out.println("UPLOAD_DIR : "+UPLOAD_DIR);
		
		try {
			// File READ - way 1
//			InputStream instream = multipartFile.getInputStream();
//			byte data[] = new byte[instream.available()];
//			instream.read(data);			// InputStream se pura ka pura data aayega, aur data me store hojayega
			
			
			// File READ - way 2
			byte[] data = multipartFile.getBytes();	
			
			// File WRITE 
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());			// Also we can use File.separator in place of "\\"  
				System.out.println("Images has been Uploaded to : "+UPLOAD_DIR +File.separator +multipartFile.getOriginalFilename());
			fos.write(data);
			
			fos.close();
		
			
/*			// File READ & WRITE - way 3
			Files.copy(	multipartFile.getInputStream(),
						Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()) , 
						StandardCopyOption.REPLACE_EXISTING	);											// File.copy(inputSteam, target path object, options); // Option matlab File kis tarike se write honi chahiye 
*/			
			b = true;
			 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
}
