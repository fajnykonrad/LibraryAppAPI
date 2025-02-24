package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.library_api.service.BookService;
import com.example.library_api.model.Book;

@RestController
@RequestMapping("/book") // API base path
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
/* 
    @GetMapping("/search")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }
    @GetMapping("/search")
    public List<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBooksByIsbn(isbn);
    }
    @GetMapping("/search")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }
*/
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
