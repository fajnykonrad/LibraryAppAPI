package com.example.library_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String mail;
    private int penalties;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
    // Getters and setters
    public User(String name, String mail, int penalties, Library library) {
        this.name = name;
        this.mail = mail;
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

    public int getPenalties() {
        return penalties;
    }

    public void addPenalty() {
        this.penalties++;
    }

    public Library getLibrary() {
        return library;
    }
}