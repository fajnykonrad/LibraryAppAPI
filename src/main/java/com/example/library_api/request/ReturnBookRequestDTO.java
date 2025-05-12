package com.example.library_api.request;

import java.sql.Date;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class ReturnBookRequestDTO {
    
    private int rentalId;
    private String bookCondition;

    // Constructors
    public ReturnBookRequestDTO() {}    
    public ReturnBookRequestDTO(int rentalId, String bookCondition) {
        this.rentalId = rentalId;
        this.bookCondition = bookCondition;
    }

    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    public String getBookCondition() { return bookCondition; }
    public void setBookCondition(String bookCondition) { this.bookCondition = bookCondition;}
}
