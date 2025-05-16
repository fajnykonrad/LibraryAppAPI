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
import com.example.library_api.model.Rental;
import com.example.library_api.model.User;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.request.RentBookRequestDTO;
import com.example.library_api.request.ReturnBookRequestDTO;
import com.example.library_api.response.BookListResponseDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;
import com.example.library_api.service.BookService;
import com.example.library_api.service.LibraryService;
import com.example.library_api.service.RentalService;
import com.example.library_api.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/libraries")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{libraryId}/rentals")
    public List<BookListResponseDTO> getRentals(@PathVariable int libraryId) {   
        return rentalService.getRentalsByLibrary(libraryId);
    }

    @PostMapping("/{libraryId}/rentals")
    public ResponseEntity<Map<String, String>> saveRental(@PathVariable int libraryId, @RequestBody RentBookRequestDTO rentalRequest) {   
        rentalService.rentBook(rentalRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book Rented Successfully");
        return ResponseEntity.ok(response);
    } 
    @PostMapping("/{libraryId}/returns") 
    public ResponseEntity<Map<String, String>> returnRental(@PathVariable int libraryId, @RequestBody ReturnBookRequestDTO returnRequest) {
        rentalService.returnBook(returnRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book Returned Successfully");
        return ResponseEntity.ok(response);
    }
}