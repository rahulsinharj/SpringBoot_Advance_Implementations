package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Book;
import com.service.BookService;

@SpringBootApplication
public class SbootGraphQlApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(SbootGraphQlApplication.class, args);
		
		System.out.println("SBootApp_GraphQL running !");
	}

	
	@Override
	public void run(String... args) throws Exception 
	{
/*		Book b1 = new Book(); 
		b1.setTitle("Java Programming");
		b1.setDesciption("For Learning Java8");
		b1.setAuthor("JavaGuru");
		b1.setPrice(1200);
		b1.setPages(8000);

		Book b2 = new Book(); 
		b2.setTitle("Pyhon Programming");
		b2.setDesciption("For Learning Python4");
		b2.setAuthor("PythonGuru");
		b2.setPrice(800);
		b2.setPages(4000);
		
		Book b3 = new Book(); 
		b3.setTitle("Spring Programming");
		b3.setDesciption("For Learning Spring5");
		b3.setAuthor("SpringGuru");
		b3.setPrice(1000);
		b3.setPages(6000);
		
		this.bookService.create(b1);
		this.bookService.create(b2);
		this.bookService.create(b3);
*/		
		
	}

}
