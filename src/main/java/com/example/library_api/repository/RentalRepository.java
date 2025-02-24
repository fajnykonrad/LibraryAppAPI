package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.Rental;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    //List<Rental> findByUserContainingIgnoreCase(String user);
}