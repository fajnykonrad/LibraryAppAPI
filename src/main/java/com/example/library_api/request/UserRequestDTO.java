package com.example.library_api.request;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class UserRequestDTO {
    
    private User user;
    private int libraryId;
    private Role role;  

    // Constructors
    public UserRequestDTO() {}

    public UserRequestDTO(String name, String email, int libraryId) {
        
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Role getRole() { return role; }
    public void setRole(Role role){ this.role = role; }
    public int getLibraryId() { return libraryId; }
    public void setLibraryId(int libraryId) { this.libraryId = libraryId; }
}
