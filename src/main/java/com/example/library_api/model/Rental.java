package com.example.library_api.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int id;

    private Date start_date;
    private Date end_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    @JsonBackReference
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;
    // Getters and setters
    public Rental() {}
    public Rental(Date end_date, User user, Supervisor supervisor, Book book) {
        this.end_date = end_date;
        this.user = user;
        this.supervisor = supervisor;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public User getUser() {
        return user;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Book getBook() {
        return book;
    }
    
}