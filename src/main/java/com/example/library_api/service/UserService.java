package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Library;
import com.example.library_api.model.User;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.request.LoginRequestDTO;
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
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (userRequest.getUser().getName().equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (userRequest.getUser().getPassword().equals("")) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (userRequest.getConfirmPassword().equals("")) {
            throw new IllegalArgumentException("Confirm password cannot be null or empty.");
        }
        if (!userRequest.getUser().getPassword().equals(userRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        if (userExists(userRequest.getUser().getMail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (!userRequest.getUser().getPassword().equals(userRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        User user = userRequest.getUser();
        userRepository.save(user);
        
    }
    public int login (LoginRequestDTO loginRequest) {
        User user = userRepository.findByMail(loginRequest.getMail())
            .orElseThrow(() -> new IllegalArgumentException("Mail isn't registered"));
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return user.getId();
        } else {
            throw new IllegalArgumentException("Incorrect Password");
        }
    }
    
}
