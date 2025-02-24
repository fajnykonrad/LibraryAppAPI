package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library_api.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByIsbn(String isbn);
}