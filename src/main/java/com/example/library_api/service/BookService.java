package com.example.library_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_api.error.GlobalExceptionHandler;
import com.example.library_api.model.Book;
import com.example.library_api.model.Library;
import com.example.library_api.model.Rental;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.RentalRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.repository.UserRoleRepository;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.BookListResponseDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;

@Service
public class BookService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public List<BookListResponseDTO> getBooksByLibrary (int libraryId) {
        Library library = libraryRepository.findById(libraryId)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        List<Book> books = bookRepository.findBooksByLibrary(library);
        List<BookListResponseDTO> bookList = new ArrayList<>();
        for (Book book : books) {
            Rental rental = rentalRepository.findCurrentRentalByBook(book)
                .orElse(null);
            BookListResponseDTO bookDTO;
            if(rental != null) {
                bookDTO = new BookListResponseDTO(book, rental);
            }
            else {
                bookDTO = new BookListResponseDTO(book);
            }
            bookList.add(bookDTO);
        }
        return bookList;
    }

    @Transactional

    public void saveBook (Book book, int libraryId) {
        Library library = libraryRepository.findById(libraryId)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        book.setLibrary(library);
        bookRepository.save(book);
    }

    public BookListResponseDTO getBookById (int libraryId, int bookId) {
        Library library = libraryRepository.findById(libraryId)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if(book.getLibrary() != library) {
            throw new IllegalArgumentException("Book not in the same library");
        }
        Rental rental = rentalRepository.findCurrentRentalByBook(book)
            .orElse(null);
        BookListResponseDTO bookDTO;
        if(rental != null) {
            bookDTO = new BookListResponseDTO(book, rental);
        }
        else {
            bookDTO = new BookListResponseDTO(book);
        }
        return bookDTO;
    }
}
