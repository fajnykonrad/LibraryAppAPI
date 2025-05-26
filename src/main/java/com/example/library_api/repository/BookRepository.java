package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.Book;
import com.example.library_api.model.Library;
import com.example.library_api.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT ur FROM Book ur WHERE ur.library = :library AND ur.isDeleted = false")
    List<Book> findBooksByLibrary(@Param("library") Library library);
    @Query("SELECT COUNT(b) FROM Book b WHERE b.library = :library AND b.isDeleted = false")
    int countBooksInLibrary(@Param("library") Library library);
    Optional<Book> findByIsbn(String isbn);
}