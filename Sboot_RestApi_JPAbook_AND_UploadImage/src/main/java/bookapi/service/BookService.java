package bookapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookapi.dao.BookRepository;
import bookapi.entity.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	 // Get All Books
    public List<Book> getAllBooks()
    {
    	List<Book> bookList = this.bookRepository.findAll();
    	return bookList;
    }

    // Get single Book by ID
    public Book getSingleBookById(int id)
    {
       Book book = null;
       try {
		book = this.bookRepository.findById(id).get();
       } 
       catch (Exception e) {
    	   System.out.println("No such item with ID = "+id);
		e.printStackTrace();
       }
       
       return book;
    }

	public Book saveBook(Book b) 
	{
		Book bk = this.bookRepository.save(b);
		return bk;
	}
//    public List<Book> saveBookandGetAll(Book b)
//    {
//    	Book bk = this.bookRepository.save(b);
//    	return (List<Book>)this.bookRepository.findAll(); 
//    }
    

	public void deleteBookById(int bid)
	{
		this.bookRepository.deleteById(bid);
	}
	
	
	public Book updateBook(int bid, Book newbook) 
	{
		newbook.setId(bid);
		this.bookRepository.save(newbook);
	
		return this.bookRepository.findById(bid).get();
	}
	
	
}
