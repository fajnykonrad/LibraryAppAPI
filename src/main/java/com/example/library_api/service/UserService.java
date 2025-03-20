package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Library;
import com.example.library_api.model.User;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.request.UserRequestDTO;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean userExists(String mail) {
        return userRepository.existsByMail(mail);
    }

    public void createUser(UserRequestDTO userRequest) {

        // Validate input fields in the service layer
        if (userRequest.getUser().getMail().equals("")) {
            throw new RuntimeException("Email cannot be null or empty.");
        }
        if (userRequest.getUser().getName().equals("")) {
            throw new RuntimeException("Name cannot be null or empty.");
        }
        if (userRequest.getUser().getPassword().equals("")) {
            throw new RuntimeException("Password cannot be null or empty.");
        }
        if (userRequest.getConfirmPassword().equals("")) {
            throw new RuntimeException("Confirm password cannot be null or empty.");
        }
        if (!userRequest.getUser().getPassword().equals(userRequest.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        if (userExists(userRequest.getUser().getMail())) {
            throw new RuntimeException("Email already in use");
        }
        if (!userRequest.getUser().getPassword().equals(userRequest.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        
        User user = userRequest.getUser();
        userRepository.save(user);
        
    }
}
