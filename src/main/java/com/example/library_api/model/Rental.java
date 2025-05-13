package com.example.library_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private User supervisor;

    @Column(name = "rental_date")
    private Date rentalDate = new Date(System.currentTimeMillis());
    @Column(name = "due_date")
    private Date dueDate;
    private Boolean returned = false;

    public Rental() {}

    public Rental(Book book, User member, User supervisor, Date dueDate) {
        this.book = book;
        this.member = member;
        this.supervisor = supervisor;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public User getMember() {return member;}
    public void setMember(User member) {this.member = member;}
    public User getSupervisor() {return supervisor;}
    public void setSupervisor(User supervisor) {this.supervisor = supervisor;}
    public Date getRentalDate() { return rentalDate; }
    public void setRentalDate(Date rentalDate) { this.rentalDate = rentalDate; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public Boolean getReturned() {return returned;}
    public void setReturned(Boolean returned) {this.returned = returned;}
}