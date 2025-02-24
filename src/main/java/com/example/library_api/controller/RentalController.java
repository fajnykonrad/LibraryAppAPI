package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.library_api.model.Rental;
import com.example.library_api.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/rental") // Base path for rental-related API requests
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{rental_id}")
    public Rental getRentalById(@PathVariable int id) {
        return rentalService.getSupervisorByRental(id);
    }

    @PostMapping
    public Rental addRental(@RequestBody Rental rental) {
        return rentalService.addRental(rental);
    }

    @DeleteMapping("/{rental_id}")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }
}
