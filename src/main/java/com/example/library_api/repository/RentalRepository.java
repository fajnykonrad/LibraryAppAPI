package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_api.model.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByUserContainingIgnoreCase(String user);
    List<Rental> findByTitleContainingIgnoreCase(String title);
}