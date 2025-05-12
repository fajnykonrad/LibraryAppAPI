package com.example.library_api.request;

import java.sql.Date;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class RentBookRequestDTO {
    
    private int bookId;
    private int memberId;
    private int supervisorId;
    private Date dueDate;
    

    // Constructors
    public RentBookRequestDTO() {}

    public RentBookRequestDTO(int bookId, int memberId, int supervisorId, Date dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.supervisorId = supervisorId;
        this.dueDate = dueDate;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public int getSupervisorId() { return supervisorId; }
    public void setSupervisorId(int supervisorId) { this.supervisorId = supervisorId; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

}
