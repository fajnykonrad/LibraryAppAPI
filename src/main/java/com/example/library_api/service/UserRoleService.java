package com.example.library_api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Library;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.repository.UserRoleRepository;
import com.example.library_api.request.AddUserRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;

import jakarta.transaction.Transactional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public List<User> getUsersByRole(int libraryId, Role role) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        return userRoleRepository.findUsersByLibraryAndRole(library, role);
    }

    public List<User> getUsersInLibrary(int libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        return userRoleRepository.findUsersInLibrary(library);
    }

    @Transactional
    public void addUserToLibrary(AddUserRequestDTO addUserRequest) {
        User user = userRepository.findByMail(addUserRequest.getMail())
                .orElseThrow(() -> new IllegalArgumentException("User not found")) ;

        Library library = libraryRepository.findById(addUserRequest.getLibraryId())
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        userRoleRepository.findRoleByUserAndLibrary(user, library)
                .ifPresentOrElse(
                        existingRole -> {
                            existingRole.setRole(addUserRequest.getRole());
                            userRoleRepository.save(existingRole);
                        },
                        () -> userRoleRepository.save(new UserRole(user, library, addUserRequest.getRole()))
                );
    }

    @Transactional
    public void removeUserFromLibrary(int libraryId, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        userRoleRepository.findRoleByUserAndLibrary(user, library)
                .ifPresent(userRoleRepository::delete);
    }
}
