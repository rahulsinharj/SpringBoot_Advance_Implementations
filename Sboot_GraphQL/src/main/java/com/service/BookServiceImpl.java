package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Book;
import com.repository.BookRepo;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public Book create(Book book) {
		return this.bookRepo.save(book);
	}

	@Override
	public List<Book> getAll() {
		return this.bookRepo.findAll();
	}

	@Override
	public Book get(int bookId) {
		return this.bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book you are looking for not found on server !"));
	}

}
