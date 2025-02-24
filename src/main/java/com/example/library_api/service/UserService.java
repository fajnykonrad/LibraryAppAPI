package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
