package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Book;
import com.example.library_api.model.Library;
import com.example.library_api.model.User;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.request.UserRequest;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public UserService(UserRepository userRepository, LibraryRepository libraryRepository) {
        this.userRepository = userRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findByIsDeletedFalse();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
/* 
    public List<User> getUserByMail(String mail) {
        return userRepository.findByMailContainingIgnoreCase(mail);
    }

    public List<User> getUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
*/
    public User addUser(UserRequest userRequest) {
        Library library = libraryRepository.findById(userRequest.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Library not found"));

        User user = userRequest.getUser();
        user.setLibrary(library);

        return userRepository.save(user);
    }
    @Transactional
    public boolean softDeleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.softDeleteUser(id); 
        return true; 
    }
}
