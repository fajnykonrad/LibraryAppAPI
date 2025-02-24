package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Rental;
import com.example.library_api.repository.RentalRepository;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getSupervisorByRental(int id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental addRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(int id) {
        rentalRepository.deleteById(id);
    }
}
