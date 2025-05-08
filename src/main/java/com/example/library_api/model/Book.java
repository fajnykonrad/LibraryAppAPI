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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    private String isbn;
    private String title;
    private String authors;
    private String publisher;
    private String categories;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "book_condition")
    private String bookCondition;

    @Column(name = "cover_picture")
    private String coverPicture;

    public Book() {}

    public Book(Library library, String isbn, String title, String authors) {
        this.library = library;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
    }

    public int getId() { return id; }
    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }
    public String getBookCondition() { return bookCondition; }
    public void setBookCondition(String bookCondition) {this.bookCondition = bookCondition;}
    public String getCoverPicture() { return coverPicture; }
    public void setCoverPicture(String coverPicture) { this.coverPicture = coverPicture; }
    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }
    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
}