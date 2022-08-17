package com.service;

import java.util.List;

import com.entity.Book;

public interface BookService {

	// Create Book
	Book create(Book book);
	
	// Get All Book 
	List<Book> getAll();
	
	// Get Single Book
	Book get(int bookId);
	
	
}
