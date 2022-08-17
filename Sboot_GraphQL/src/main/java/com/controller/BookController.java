package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.entity.Book;
import com.service.BookService;

import lombok.Getter;
import lombok.Setter;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	// Create Book
	@MutationMapping("createBook")				// - for insertion of data
	public Book createBook(@Argument BookInput book) {			// Parameter argument naming convention should be exactly same as in schema.graphql
																		// createBook(book:BookInput):Book
		Book b = new Book();
		b.setTitle(book.getTitle());
		b.setDesciption(book.getDesciption());
		b.setAuthor(book.getAuthor());
		b.setPrice(book.getPrice());
		b.setPages(book.getPages());
		
		return this.bookService.create(b);
	}

	// Get Single Book
	@QueryMapping("getBook")
	public Book getSingleBook(@Argument("bookId") int bookId) {
		return this.bookService.get(bookId);
	}
	
	// Get All Book 
//	@SchemaMapping  -  it can be used for 3 uses : @QueryMapping, @MutationMapping, and @SubcriptionMapping
	@QueryMapping("allBooks")		// - only for mapping our get queries
	public List<Book> getAllBook() {
		return this.bookService.getAll();
	}

}	

@Getter
@Setter
class BookInput
{
	private String title;
	private String desciption;
	private String author;
	private double price;
	private int pages;
}

	
/*======================================================================================
  		query{
		    allBooks{
		        id
		        title
		        desciption
		        pages
		    }
		}
    
*/

/* 
 		query{
		    getBook(bookId:2){
		        id
		        title
		        pages
		    }
		}
*/

/*
		mutation{
		    createBook(book:{
		        title: "MySQL Book",
		        desciption: "MySQL book description"
		        author: "MySQL RJ",
		        price: 4887,
		        pages: 888
		    }){
		        id
		        title
		        author
		        price
		    }
		}

*/
	
