package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.Supervisor;

import java.util.List;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    //List<Supervisor> findByNameContainingIgnoreCase (String name);
    //List<Supervisor> findByMailContainingIgnoreCase (String mail);
}