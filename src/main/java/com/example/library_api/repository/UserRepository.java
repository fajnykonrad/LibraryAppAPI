package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //List<User> findByNameContainingIgnoreCase (String name);
    //List<User> findByMailContainingIgnoreCase (String mail);
}