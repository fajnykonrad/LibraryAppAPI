package com.example.library_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library_api.error.ResourceNotFoundException;
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
    public Library getLibraryById(int libraryId) {
        return libraryRepository.findById(libraryId)
            .orElseThrow(() -> new ResourceNotFoundException("Library with ID " + libraryId + " not found"));
    }
    public void deleteLibrary(int id) {
        libraryRepository.deleteById(id);
    }
}
