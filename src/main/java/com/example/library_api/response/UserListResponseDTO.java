package com.example.library_api.response;

import java.sql.Date;
import java.util.List;

import com.example.library_api.model.Book;
import com.example.library_api.model.Rental;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class UserListResponseDTO {
    
    private User user;
    private List<Rental> currentRentals;
    private List<Rental> pastRentals;   

    // Constructors
    public UserListResponseDTO() {}
    public UserListResponseDTO(User user) {
        this.user = user;
    }
    public UserListResponseDTO(User user, List<Rental> currentRentals, List<Rental> pastRentals) {
        this.user = user;
        this.currentRentals = currentRentals;
        this.pastRentals = pastRentals;
    }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<Rental> getCurrentRentals() { return currentRentals; }
    public void setCurrentRentals(List<Rental> currentRentals) { this.currentRentals = currentRentals; }
    public List<Rental> getPastRentals() { return pastRentals; }
    public void setPastRentals(List<Rental> pastRentals) { this.pastRentals = pastRentals;}
}
