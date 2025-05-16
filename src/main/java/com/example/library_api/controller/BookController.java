package com.example.library_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_api.model.Book;
import com.example.library_api.model.User;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.BookListResponseDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;
import com.example.library_api.service.BookService;
import com.example.library_api.service.LibraryService;
import com.example.library_api.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/libraries")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{libraryId}/books")
    public List<BookListResponseDTO> getBooksByLibrary(@PathVariable int libraryId) {   
        return bookService.getBooksByLibrary(libraryId);
    }

    @PostMapping("/{libraryId}/books")
    public ResponseEntity<Map<String, String>> saveBook(@PathVariable int libraryId, @RequestBody Book book) {   
        bookService.saveBook(book, libraryId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User Added Successfully");
        return ResponseEntity.ok(response);
    } 
    @GetMapping("/{libraryId}/books/{bookId}")
    public BookListResponseDTO getBookById(@PathVariable int libraryId, @PathVariable int bookId) {   
        return bookService.getBookById(libraryId, bookId);
    }

}