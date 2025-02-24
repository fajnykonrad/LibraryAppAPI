package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.library_api.model.Rental;
import com.example.library_api.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/rentals") // Base path for rental-related API requests
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

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable int id) {
        return rentalService.getSupervisorByRental(id);
    }

    @GetMapping("/search")
    public List<Rental> getRentalsByTitle(@RequestParam String title) {
        return rentalService.getRentalsByTitle(title);
    }

    @GetMapping("/search")
    public List<Rental> getRentalsByUser(@RequestParam String username) {
        return rentalService.getRentalsByUser(username);
    }

    @PostMapping
    public Rental addRental(@RequestBody Rental rental) {
        return rentalService.addRental(rental);
    }

    // 📌 Delete a rental by ID
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }
}
