package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.Book;
import com.example.library_api.model.Library;
import com.example.library_api.model.Rental;
import com.example.library_api.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findRentalsByMember(User member);
    List<Rental> findRentalsBySupervisor(User supervisor);
    
    @Query("SELECT r FROM Rental r INNER JOIN r.book WHERE r.book.library = :library AND r.returned = false")
    List<Rental> findRentalsByLibrary(@Param("library") Library library);

    @Query("SELECT r FROM Rental r WHERE r.book = :book AND r.returned = false")
    Optional<Rental> findCurrentRentalByBook(@Param("book") Book book);
    @Query("SELECT r FROM Rental r WHERE r.member = :member AND r.book.library = :library AND r.returned = false ORDER BY r.rentalDate DESC")
    List<Rental> findCurrentRentalsByMemberAndLibrary(@Param("member") User member, @Param("library") Library library, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT r FROM Rental r WHERE r.member = :member AND r.book.library = :library AND r.returned = true ORDER BY r.rentalDate DESC")
    List<Rental> findPastRentalsByMemberAndLibrary(@Param("member") User member, @Param("library") Library library, org.springframework.data.domain.Pageable pageable);
}