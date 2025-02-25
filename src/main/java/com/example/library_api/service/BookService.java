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


    // Delete a book by ID
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
