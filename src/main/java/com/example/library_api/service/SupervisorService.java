package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Supervisor;
import com.example.library_api.repository.SupervisorRepository;

@Service
public class SupervisorService {
    @Autowired
    private SupervisorRepository supervisorRepository;

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    public Supervisor getSupervisorById(int id) {
        return supervisorRepository.findById(id).orElse(null);
    }
    /* 
    public List<Supervisor> getSupervisorByMail(String mail) {
        return supervisorRepository.findByMailContainingIgnoreCase(mail);
    }

    public List<Supervisor> getSupervisorByName(String name) {
        return supervisorRepository.findByNameContainingIgnoreCase(name);
    }
        */

    public Supervisor addSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public void deleteSupervisor(int id) {
        supervisorRepository.deleteById(id);
    }
}
