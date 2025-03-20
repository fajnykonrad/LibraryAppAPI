package com.example.library_api.request;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class AddUserRequestDTO {
    
    private String mail;
    private int libraryId;
    private Role role;
    

    // Constructors
    public AddUserRequestDTO() {}

    public AddUserRequestDTO(String email, int libraryId) {
        
    }

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public int getLibraryId() { return libraryId; }
    public void setLibraryId(int libraryId) { this.libraryId = libraryId; }
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
}
