package myform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import myform.dao.StudentFormDao;
import myform.entity.StudentForm;

@Controller
public class FormController {

	@Autowired
	private StudentFormDao studentFormDao;
	
	@ResponseBody
	@RequestMapping("/")
	public String reurl() 
	{
		// Or either we can do this redirection as well =>			return "redirect:complex";
		return "Welcome, please use these URLs : /complex ,  /fileform  ,  /add" ;
	}
	
	
	@RequestMapping("/complex")
	public String showform() {
		return "complexform";
	}
	
	
	@PostMapping("/handleform")
	public String handleform(@ModelAttribute("stuform") StudentForm receivedStuForm , BindingResult result)
	{
		if(result.hasErrors()) {
			System.out.println("Error redirecting to complexform again..");
			return "complexform" ;
		}
		System.out.println(receivedStuForm);			// Student entries received from Student Form entries.
		
		StudentForm savedDBStudentForm = this.studentFormDao.saveStudentForm(receivedStuForm);
		System.out.println(savedDBStudentForm);			// Student entries received from DB after storing Student Form entries.
		
		return "success";
	}
	
	
	@GetMapping("/getstudents")
	@ResponseBody										// ReponseBody lagaye hai, because @Controller class me WEBPAGE ke bajeye text/data/value return karne ke liye ye Annotation lagana padta hai. 
	public List<StudentForm> getAllStudents()					// RestController hota to ResponseBody annotation nhi lagana padta.
	{
		List<StudentForm> allStuList =  this.studentFormDao.getAllStudentsList();
		for(StudentForm s : allStuList ) {
			System.out.println(s);
		}
		
		return allStuList;
	}
	

/*
  Below codes are just for testing Exception Handler ::
 -------------------------------------------------------------------------------------------*/
	
	@RequestMapping("/add")
	public String takeAddition() {
		return "add";
	}
	
	@RequestMapping("/addresult")
	public String calulateAddResult(@RequestParam("num1") String n1, @RequestParam("num2") String n2 , Model m) {
		
		if(n1.isBlank() || n1.isBlank()) {
			return "redirect:add";
		}
		
//		String s1 = null;
//		s1 += s1.charAt(0);
		
		long sum = Long.parseLong(n1)+ Long.parseLong(n2);
		m.addAttribute("addans", sum);
		System.out.println("Addition of "+n1 +" + "+n2 +" = "+sum);
		
		return "add";			// also we can use =>   return "redirect:add" ;
	}

	
	
	
}
