package com.example.library_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.library_api.model.Supervisor;
import com.example.library_api.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/supervisors") // Base path for supervisor-related API requests
public class SupervisorController {

    private final SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping
    public List<Supervisor> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/{id}")
    public Supervisor getSupervisorById(@PathVariable int id) {
        return supervisorService.getSupervisorById(id);
    }

    @GetMapping("/search")
    public List<Supervisor> getSupervisorByMail(@RequestParam String mail) {
        return supervisorService.getSupervisorByMail(mail);
    }

    @GetMapping("/search")
    public List<Supervisor> getSupervisorByName(@RequestParam String name) {
        return supervisorService.getSupervisorByName(name);
    }

    @PostMapping
    public Supervisor addSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.addSupervisor(supervisor);
    }

    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable int id) {
        supervisorService.deleteSupervisor(id);
    }
}
