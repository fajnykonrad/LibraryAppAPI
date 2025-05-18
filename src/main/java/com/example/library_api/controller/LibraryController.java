package com.example.library_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_api.model.Library;
import com.example.library_api.model.User;
import com.example.library_api.request.CreateLibraryRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;
import com.example.library_api.service.LibraryService;
import com.example.library_api.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/user/{userId}")
    public List<LibraryRoleResponseDTO> getLibrariesByUser(@PathVariable int userId) {    
        return libraryService.getLibrariesByUser(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createLibrary(@RequestBody CreateLibraryRequestDTO createLibraryRequest) {
        libraryService.createLibrary(createLibraryRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Library Created Successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String,String>> joinLibrary(@RequestBody CreateLibraryRequestDTO createLibraryRequest) {
        libraryService.joinLibraryByCode(createLibraryRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Joined Library Successfully");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{libraryId}")
    public Library getLibraryById(@PathVariable int libraryId) {
        return libraryService.getLibraryById(libraryId);
    }
    
    

}