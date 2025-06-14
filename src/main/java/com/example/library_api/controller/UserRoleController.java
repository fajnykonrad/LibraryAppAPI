package com.example.library_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.request.AddUserRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.UserListResponseDTO;
import com.example.library_api.service.UserRoleService;
import com.example.library_api.service.UserService;

@RestController
@RequestMapping("/libraries")
public class UserRoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/{libraryId}/users/{userRole}")
    public ResponseEntity<List<UserListResponseDTO>> getUsers(@PathVariable int libraryId, @PathVariable Role userRole) {
        List<UserListResponseDTO> users = userRoleService.getUsersByRole(libraryId, userRole);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{libraryId}/users")
    public ResponseEntity<Map<String, String>> addUserToLibrary(@RequestBody AddUserRequestDTO addUserRequest) {
        userRoleService.addUserToLibrary(addUserRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User Added Successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{libraryId}/users/{userId}")
    public ResponseEntity<String> removeUserFromLibrary(
            @PathVariable int libraryId,
            @PathVariable int userId) {
        userRoleService.removeUserFromLibrary(libraryId, userId);
        return ResponseEntity.ok("User removed successfully.");
    }
    
}
