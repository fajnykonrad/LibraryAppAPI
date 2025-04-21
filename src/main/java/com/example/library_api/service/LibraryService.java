package com.example.library_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_api.error.GlobalExceptionHandler;
import com.example.library_api.model.Library;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.repository.UserRoleRepository;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LibraryRoleResponseDTO> getLibrariesByUser (int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Library> libraries = new ArrayList<>(userRoleRepository.findLibrariesByUser(user));
        
        List<LibraryRoleResponseDTO> LibrariesAndRoles = new ArrayList<>();

        for(Library library : libraries) {
            UserRole userRole = userRoleRepository.findRoleByUserAndLibrary(user, library)
                .orElseThrow(() -> new IllegalArgumentException("User not in library"));
            LibraryRoleResponseDTO libraryAndRole = new LibraryRoleResponseDTO(library, userRole.getRole());
            LibrariesAndRoles.add(libraryAndRole);
        }
        return LibrariesAndRoles;
    }
}
