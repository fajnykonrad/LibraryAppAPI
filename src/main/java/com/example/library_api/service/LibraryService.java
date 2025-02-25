package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Library;
import com.example.library_api.repository.LibraryRepository;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library addLibrary(Library rental) {
        return libraryRepository.save(rental);
    }

    public void deleteRLibrary(int id) {
        libraryRepository.deleteById(id);
    }
}
