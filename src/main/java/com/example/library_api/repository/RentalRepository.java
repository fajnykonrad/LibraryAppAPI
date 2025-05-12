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
    @Query("SELECT r FROM Rental r WHERE r.book = :book AND r.returned = true")
    Optional<Rental> findIfBookRented(@Param("book") Book book);
    List<Rental> findRentalsByMember(User member);
    List<Rental> findRentalsBySupervisor(User supervisor);
    
    @Query("SELECT r FROM Rental r INNER JOIN r.book WHERE r.book.library = :library AND r.returned = false")
    List<Rental> findRentalsByLibrary(@Param("library") Library library);

    @Query("SELECT r FROM Rental r WHERE r.book = :book AND r.returned = false")
    Optional<Rental> findCurrentRentalByBook(@Param("book") Book book);
}