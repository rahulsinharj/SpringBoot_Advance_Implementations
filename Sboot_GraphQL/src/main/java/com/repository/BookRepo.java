package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
