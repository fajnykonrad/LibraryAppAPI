package com.example.library_api.request;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;

public class CreateLibraryRequestDTO {
    
    private int userId;
    private String name;
        // Constructors
    public CreateLibraryRequestDTO() {}

    public CreateLibraryRequestDTO(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

}
