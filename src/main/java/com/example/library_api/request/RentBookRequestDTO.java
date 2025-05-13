package com.example.library_api.request;

import java.sql.Date;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class RentBookRequestDTO {
    
    private int bookId;
    private String memberMail;
    private int supervisorId;
    private Date dueDate;
    

    // Constructors
    public RentBookRequestDTO() {}

    public RentBookRequestDTO(int bookId, String memberMail, int supervisorId, Date dueDate) {
        this.bookId = bookId;
        this.memberMail = memberMail;
        this.supervisorId = supervisorId;
        this.dueDate = dueDate;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getMemberMail() { return memberMail; }
    public void setMemberMail(String memberMail) { this.memberMail = memberMail; }
    public int getSupervisorId() { return supervisorId; }
    public void setSupervisorId(int supervisorId) { this.supervisorId = supervisorId; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

}
