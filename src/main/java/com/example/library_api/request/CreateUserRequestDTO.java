package com.example.library_api.request;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class CreateUserRequestDTO {
    
    private User user;
    private String confirmPassword;

    // Constructors
    public CreateUserRequestDTO() {}

    public CreateUserRequestDTO(String name, String mail, String password, Role role, int libraryId) {
        this.user = new User(name, mail, password);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
