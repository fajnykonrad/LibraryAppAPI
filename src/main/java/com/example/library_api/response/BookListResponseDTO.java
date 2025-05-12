package com.example.library_api.response;

import java.sql.Date;

import com.example.library_api.model.Book;
import com.example.library_api.model.Rental;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class BookListResponseDTO {
    
    private Book book;
    private Rental rental;

    // Constructors
    public BookListResponseDTO() {}
    public BookListResponseDTO(Book book) {
        this.book = book;
    }
    public BookListResponseDTO(Book book, Rental rental) {
        this.book = book;
        this.rental = rental;
    }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }
}
