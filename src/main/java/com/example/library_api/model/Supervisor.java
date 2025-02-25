package com.example.library_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervisor_id")
    private int id;

    private String name;
    private String mail;
    private String password;

    

    @ManyToOne
    @JoinColumn(name = "library_id")  // Foreign key to Publisher
    @JsonBackReference
    private Library library;
    // Getters and setters
    public Supervisor() {}
        
    public Supervisor(String name, String mail, String password, Library library) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Library getLibrary() {
        return library;
    }
    
}