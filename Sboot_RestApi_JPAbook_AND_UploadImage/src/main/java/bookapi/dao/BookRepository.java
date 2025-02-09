package bookapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bookapi.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
