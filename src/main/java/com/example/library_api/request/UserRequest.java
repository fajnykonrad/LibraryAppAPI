package com.example.library_api.request;

import com.example.library_api.model.User;

public class UserRequest {
    
    private User user;
    private int libraryId;  

    // Constructors
    public UserRequest() {}

    public UserRequest(String name, String email, int libraryId) {
        
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getLibraryId() { return libraryId; }
    public void setLibraryId(int libraryId) { this.libraryId = libraryId; }
}
