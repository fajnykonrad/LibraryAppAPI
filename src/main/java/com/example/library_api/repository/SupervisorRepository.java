package com.example.library_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library_api.model.Supervisor;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    List<Supervisor> findByNameContainingIgnoreCase (String name);
    List<Supervisor> findByMailContainingIgnoreCase (String mail);
}