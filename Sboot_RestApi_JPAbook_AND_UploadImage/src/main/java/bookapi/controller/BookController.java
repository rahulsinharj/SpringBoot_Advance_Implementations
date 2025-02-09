package bookapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookapi.entity.Book;
import bookapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;	
	
//==============[ swagger-ui/ springdoc-openapi-ui => Explanations for what does this API do ]==============
	
	@Operation(summary = "This is to fetch all the books stored in Db")			
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200",
			    		description = "Fetched all books from Db" ,
			    		content = {@Content(mediaType = "application/json")}) ,
    		@ApiResponse(responseCode = "404",
    					description = "Record not available" ,
    					content = @Content)
    })
	@GetMapping("/")										// Handler for - Getting All Books 
    public ResponseEntity<List<Book>> getAllBooks() 
    {
    	List<Book> allBooks = this.bookService.getAllBooks();
    	if(allBooks.size() <= 0) 														// If no books found, then we should return Response as "Not Found" as STATUS to the front server 
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	
    	return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }
    
       
    @GetMapping("/{id}")									// Handler for - Getting Single Book 
    public ResponseEntity<Book> getSingleBook(@PathVariable("id") int id) 
    {
    	Book b= this.bookService.getSingleBookById(id);
    	if(b == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    		System.out.println(b);
    	return ResponseEntity.of(Optional.of(b));
    }
    
    
/*   [POST MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/   
    
    // Post and get all BOOKS :
//    @PostMapping("/")										// Handler for - Creating new Book
//    public ResponseEntity<List<Book>> addBookandSee(@RequestBody Book book) 				// @RequestBody - same way @RequestParam
//    {
//    	List<Book> b = null;
//    	try {
//			b= this.bookService.saveBookandGetAll(book);
//			return ResponseEntity.status(HttpStatus.CREATED).body(b);
//		} 
//    	catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    	}
//    }
    
    
//     POST and get that only added book ::
    @Operation(summary = "This is to create a new book and storing in Db")		
    @PostMapping("/")										// Handler for - Creating new Book
    public ResponseEntity<Book> addBook(@RequestBody Book book) 				// @RequestBody - same way @RequestParam
    {
    	Book b = null;
    	try {
			b= this.bookService.saveBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} 
    	catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    
/*   [UPDATE MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/
    
    // Updating and 
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book )		// Handler for - updating single Book
    {
    	Book updatedbook = null;
    	try {
			updatedbook = this.bookService.updateBook(bookId, book);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedbook);
    	} 
    	catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    
/*   [DELETE MAPPING] ::
 -------------------------------------------------------------------------------------------------------------------*/    

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId )		// Handler for - deleting single Book
	{
		try {
			this.bookService.deleteBookById(bookId);
//			return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
		}
	  	catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
	  
/* [JSON Object send from client ::]
   ----------------------------------
		    {
        		"id": 1,
        		"title": "Java Certification Book",
        		"author": {
            			"authorId": 1,
            			"firstName": "Rahul",
            			"lastName": "Sinha",
            			"language": "English"
				 }
    		}



*/
	
    
    
}
