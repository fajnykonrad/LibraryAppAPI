package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Book;
import com.example.library_api.model.Library;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.LibraryRepository;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
    public Book addBook(int libraryId, String title) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found"));

        Book book = new Book(title, library);
        return bookRepository.save(book);
    }
}
