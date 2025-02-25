package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.library_api.model.Book;
import com.example.library_api.model.User;
import com.example.library_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/user") // Base path for user-related API requests
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable int user_id) {
        return userService.getUserById(user_id);
    }
/* 
    @GetMapping("/search/mail")
    public List<User> getUserByMail(@RequestParam String mail) {
        return userService.getUserByMail(mail);
    }

    @GetMapping("/search/name")
    public List<User> getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }
*/
   @PostMapping
    public ResponseEntity<User> addUser(@RequestParam int libraryId, @RequestParam String name, @RequestParam String mail) {
        User newUser = userService.addUser(libraryId, name, mail);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{user_id}")
        public ResponseEntity<String> softDeleteUser(@PathVariable int user_id) {
        boolean deleted = userService.softDeleteUserById(user_id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
}
