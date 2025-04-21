package com.example.library_api.response;

import com.example.library_api.model.Library;
import com.example.library_api.model.Role;

public class LibraryRoleResponseDTO {

    private Library library;
    private Role role;

    public LibraryRoleResponseDTO() {}
    public LibraryRoleResponseDTO(Library library, Role role) {
        this.library = library;
        this.role = role;
    }

    public Library getLibrary() {return library;}
    public void setLibrary(Library library) {this.library = library;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
}
