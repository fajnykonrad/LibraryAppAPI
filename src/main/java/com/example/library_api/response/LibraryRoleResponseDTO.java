package com.example.library_api.response;

import com.example.library_api.model.Library;
import com.example.library_api.model.Role;

public class LibraryRoleResponseDTO {

    private Library library;
    private Role role;
    private int bookCount;

    public LibraryRoleResponseDTO() {}
    public LibraryRoleResponseDTO(Library library, Role role, int bookCount) {
        this.library = library;
        this.role = role;
        this.bookCount = bookCount;
    }

    public Library getLibrary() {return library;}
    public void setLibrary(Library library) {this.library = library;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
    public int getBookCount() {return bookCount;}
    public void setBookCount(int bookCount) {this.bookCount = bookCount;}
}
