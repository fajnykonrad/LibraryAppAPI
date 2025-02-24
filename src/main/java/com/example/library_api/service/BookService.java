package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Book;
import com.example.library_api.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /*  Get books by author
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Book> getBooksByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    */
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Delete a book by ID
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
