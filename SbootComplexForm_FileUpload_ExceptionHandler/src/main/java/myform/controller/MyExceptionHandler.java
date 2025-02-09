package myform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {

/* Handling Exception for the whole Application - by using @ControllerAdvice annotation above the Class name :
 -------------------------------------------------------------------------------------------*/
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String exceptionHanderNull(Model m) {
		m.addAttribute("msg", "Null Pointer exception has occured");
		return "error";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NumberFormatException.class)
	public String exceptionHanderNumberFormat(Model m) {
		m.addAttribute("msg", "Number format  exception has occured");
		return "error";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHanderGeneric(Model m) {
		m.addAttribute("msg", "Exception has occured");
		return "error";
	}

	
	
}
