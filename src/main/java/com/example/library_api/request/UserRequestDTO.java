package com.example.library_api.request;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class UserRequestDTO {
    
    private User user;
    private int libraryId;
    private String confirmPassword;
    private Role role;  

    // Constructors
    public UserRequestDTO() {}

    public UserRequestDTO(String name, String mail, String password, Role role, int libraryId) {
        this.user = new User(name, mail, password);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    public Role getRole() { return role; }
    public void setRole(Role role){ this.role = role; }
    public int getLibraryId() { return libraryId; }
    public void setLibraryId(int libraryId) { this.libraryId = libraryId; }
}
