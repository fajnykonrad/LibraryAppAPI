package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.library_api.model.Library;
import com.example.library_api.model.User;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.request.UserRequestDTO;
import com.example.library_api.service.LibraryService;
import com.example.library_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users") // Base path for user-related API requests
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User has been created");
    }
}
