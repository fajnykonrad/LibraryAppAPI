package com.example.library_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_api.error.GlobalExceptionHandler;
import com.example.library_api.model.Library;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;
import com.example.library_api.repository.BookRepository;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.repository.UserRoleRepository;
import com.example.library_api.request.CreateLibraryRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.LibraryRoleResponseDTO;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LibraryRoleResponseDTO> getLibrariesByUser (int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Library> libraries = new ArrayList<>(userRoleRepository.findLibrariesByUser(user));
        
        List<LibraryRoleResponseDTO> LibrariesAndRoles = new ArrayList<>();

        for(Library library : libraries) {
            UserRole userRole = userRoleRepository.findRoleByUserAndLibrary(user, library)
                .orElseThrow(() -> new IllegalArgumentException("User not in library"));
            int bookCount = bookRepository.countBooksInLibrary(library);
            LibraryRoleResponseDTO libraryAndRole = new LibraryRoleResponseDTO(library, userRole.getRole(), bookCount);
            LibrariesAndRoles.add(libraryAndRole);
        }
        return LibrariesAndRoles;
    }

    public void createLibrary(CreateLibraryRequestDTO createLibraryRequest) {
        if (createLibraryRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty");
        }
        String code = generateCode();
        Library library = new Library(createLibraryRequest.getName(), code);
        libraryRepository.save(library);
        Library savedLibrary = libraryRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        User user = userRepository.findById(createLibraryRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        UserRole userRole = new UserRole(user, savedLibrary, Role.SUPERVISOR);
        userRoleRepository.save(userRole);
    }

    @Transactional
    public void joinLibraryByCode(CreateLibraryRequestDTO createLibraryRequest) {
        String code = createLibraryRequest.getName();
        if (code.isEmpty()) {
            throw new IllegalArgumentException("Library code cannot be empty");
        }
        Library library = libraryRepository.findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        User user = userRepository.findById(createLibraryRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (userRoleRepository.findRoleByUserAndLibrary(user, library).isPresent()) {
            throw new IllegalArgumentException("User already in library");
        }
        userRepository.save(user);
        UserRole userRole = new UserRole(user, library, Role.MEMBER);
        userRoleRepository.save(userRole);  
    }
    public Library getLibraryById(int libraryId) {
        return libraryRepository.findById(libraryId)
            .orElseThrow(() -> new IllegalArgumentException("Library not found"));
    }    

    private String generateCode() {
        // Caracteres posibles para el código
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        while(true) {
        // Generar un código aleatorio de 6 caracteres
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // Generar un índice aleatorio para seleccionar un carácter
            int randomIndex = (int) (Math.random() * chars.length());
            // Agregar el carácter al código
            code.append(chars.charAt(randomIndex));
        }
        // Verificar si el código ya existe en la base de datos
        if(!libraryRepository.findByCode(code.toString()).isPresent()) {
            return code.toString();
        }
        }    
    }
}
