package myform.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping("/fileform")
	public String showUploadForm() {
		return "fileform";
	}


/*  Uploading A SINGLE file  ::
 -------------------------------------------------------------------------------------------*/	
	
	@RequestMapping(path = "/uploadimage", method = RequestMethod.POST)
	public String uploadSingleImage(@RequestParam("profile") MultipartFile file, HttpSession ses, Model model ) 
	{
		if(file.isEmpty()) {
			System.out.println("f : "+file );
			return "redirect:fileform";
		}
		
		System.out.println("Inside File upload handler...");

		System.out.println("File size : " +file.getSize()/1024 +" kb");				// Return the size of the file in bytes , now in Kb now
		System.out.println("File content type : " +file.getContentType());			// Return the content type of the file.
		System.out.println("Upload parameter name : " +file.getName());											// Return the name of the parameter in the multipart form.
		System.out.println("OriginalFilename : " +file.getOriginalFilename());		// Return the original filename in the client's filesystem. 
		System.out.println("Current working path : "+ ses.getServletContext().getRealPath("/") );
		System.out.println();
		
		try {
			byte[] data = file.getBytes();											// Getting the Byte data from the uploaded file.											
			String path = ses.getServletContext().getRealPath("/") + "static" + File.separator + "image"
					+ File.separator + file.getOriginalFilename();
			
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);														// Saving the file to the server.
			fos.close();
			System.out.println("File uploaded at location : "+path);
			
			model.addAttribute("filename",file.getOriginalFilename());
			model.addAttribute("msg",file.getOriginalFilename()+" Uploaded Successfully..!!");
			
		
		} catch (IOException e) {
			System.out.println("Error in uploading..");
			model.addAttribute("msg","Error in File uploading..!!");
			e.printStackTrace();
		}

		return "filesuccess";
	}

	
/*  Uploading MULTIPLE files  ::
 -------------------------------------------------------------------------------------------*/	
		
	@RequestMapping(path = "/uploadimages", method = RequestMethod.POST)
	public String uploadMultipleImages(@RequestParam("profile") MultipartFile[] multifiles, HttpSession ses, Model model ) 
	{
		ArrayList<String> upfilesnames = new ArrayList<String>();
		try 
		{	// Write here some null handler code when directly click upload button without even uploading 
			
			for(MultipartFile file : multifiles) 							// Handling multiple files
			{
				byte[] data = file.getBytes();											// Getting the Byte data from the uploaded file.											
				String path = ses.getServletContext().getRealPath("/") + "static" + File.separator + "image"
						+ File.separator + file.getOriginalFilename();
				
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(data);														// Saving the file to the server.
				fos.close();
				System.out.println("File uploaded at location : "+path);
				
				upfilesnames.add(file.getOriginalFilename());
				model.addAttribute("msg","Files Uploaded Successfully..!!");
				
			} 
		}
		catch (IOException e) {
			System.out.println("Error in uploading..");
			model.addAttribute("msg","Error in File uploading..!!");
			e.printStackTrace();
		}
		model.addAttribute("filename",upfilesnames);
		
		return "filesuccess";
	}
	
}
