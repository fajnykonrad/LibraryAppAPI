package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.library_api.model.User;
import com.example.library_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users") // Base path for user-related API requests
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

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search/mail")
    public List<User> getUserByMail(@RequestParam String mail) {
        return userService.getUserByMail(mail);
    }

    @GetMapping("/search/name")
    public List<User> getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
