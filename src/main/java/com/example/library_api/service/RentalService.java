package com.example.library_api.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.library_api.request.AddUserRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.request.RentBookRequestDTO;
import com.example.library_api.request.ReturnBookRequestDTO;
import com.example.library_api.response.BookListResponseDTO;

import jakarta.transaction.Transactional;

@Service
public class RentalService {

        @Autowired
        private RentalRepository rentalRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserRoleRepository userRoleRepository;

        @Autowired
        private BookRepository bookRepository;

        @Autowired
        private LibraryRepository libraryRepository;

        public void rentBook(RentBookRequestDTO rentBookRequest) {
                Book book = bookRepository.findById(rentBookRequest.getBookId())
                                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
                if (rentalRepository.findCurrentRentalByBook(book).isPresent()) {
                        throw new IllegalArgumentException("Book already rented");
                }
                User member = userRepository.findByMail(rentBookRequest.getMemberMail())
                                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
                User supervisor = userRepository.findById(rentBookRequest.getSupervisorId())
                                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));

                if (rentalRepository.findCurrentRentalByBook(book).isPresent()) {
                        throw new IllegalArgumentException("Book already rented");
                }
                if (!userRoleRepository.findRoleByUserAndLibrary(member, book.getLibrary()).isPresent()) {
                        throw new IllegalArgumentException("Member not in the same library as book");
                }
                if (!userRoleRepository.findRoleByUserAndLibrary(supervisor, book.getLibrary()).isPresent()) {
                        throw new IllegalArgumentException("Supervisor not in the same library as book");
                }

                Rental rental = new Rental(book, member, supervisor, rentBookRequest.getDueDate());
                rentalRepository.save(rental);
        }

        public List<Rental> getRentalsByMember(int memberId) {
                User member = userRepository.findById(memberId)
                                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
                return rentalRepository.findRentalsByMember(member);
        }

        public List<Rental> getRentalsBySupervisor(int supervisorId) {
                User supervisor = userRepository.findById(supervisorId)
                                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));
                return rentalRepository.findRentalsBySupervisor(supervisor);
        }

        public List<BookListResponseDTO> getRentalsByLibrary(int libraryId) {

                Library library = libraryRepository.findById(libraryId)
                                .orElseThrow(() -> new IllegalArgumentException("Library not found"));
                List<Rental> rentals = rentalRepository.findRentalsByLibrary(library);
                List<BookListResponseDTO> bookList = new ArrayList<>();
                for (Rental rental : rentals) {
                        Book book = rental.getBook();
                        BookListResponseDTO bookResponse = new BookListResponseDTO(book, rental);
                        bookList.add(bookResponse);
                }
                return bookList;

        }

        public void returnBook(ReturnBookRequestDTO returnBookRequest) {
                Rental rental = rentalRepository.findById(returnBookRequest.getRentalId())
                                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));
                rental.getBook().setBookCondition(returnBookRequest.getBookCondition());
                // Apply penalty if the book is damaged or returned late
                if (rental.getDueDate().before(new Date(System.currentTimeMillis())) || returnBookRequest.isDamaged()) {
                        UserRole userRole = userRoleRepository
                                        .findRoleByUserAndLibrary(rental.getMember(), rental.getBook().getLibrary())
                                        .orElseThrow(() -> new IllegalArgumentException("Member not in the same library as book"));
                        userRole.addPenalty();
                }
                if (returnBookRequest.isDamaged()) {
                        rental.setIsDamaged(true);
                }
                rental.setReturned(true);
                rentalRepository.save(rental);
        }
}
