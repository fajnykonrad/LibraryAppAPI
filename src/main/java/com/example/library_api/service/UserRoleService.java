package com.example.library_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.library_api.model.Library;
import com.example.library_api.model.Rental;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;
import com.example.library_api.repository.LibraryRepository;
import com.example.library_api.repository.RentalRepository;
import com.example.library_api.repository.UserRepository;
import com.example.library_api.repository.UserRoleRepository;
import com.example.library_api.request.AddUserRequestDTO;
import com.example.library_api.request.CreateUserRequestDTO;
import com.example.library_api.response.UserListResponseDTO;

import jakarta.transaction.Transactional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    
    @Autowired
    private RentalRepository rentalRepository;

    public List<UserListResponseDTO> getUsersByRole(int libraryId, Role role) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        List<User> users = userRoleRepository.findUsersByLibraryAndRole(library, role);
        List<UserListResponseDTO> userList = new ArrayList<>();
        for (User user : users) {
                PageRequest pageRequest = PageRequest.of(0, 5);
                List<Rental> currentRentals = rentalRepository.findCurrentRentalsByMemberAndLibrary(user, library, pageRequest);
                List<Rental> pastRentals = rentalRepository.findPastRentalsByMemberAndLibrary(user, library, pageRequest);
                UserListResponseDTO userDTO = new UserListResponseDTO(user, currentRentals, pastRentals);       
                userList.add(userDTO);
        }
        return userList;
    }

    public List<UserListResponseDTO> getUsersInLibrary(int libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));
        List<User> users = userRoleRepository.findUsersInLibrary(library);
        List<UserListResponseDTO> userList = new ArrayList<>();
        for (User user : users) {
                PageRequest pageRequest = PageRequest.of(0, 5);
                List<Rental> currentRentals = rentalRepository.findCurrentRentalsByMemberAndLibrary(user, library, pageRequest);
                List<Rental> pastRentals = rentalRepository.findPastRentalsByMemberAndLibrary(user, library, pageRequest);
                UserListResponseDTO userDTO = new UserListResponseDTO(user, currentRentals, pastRentals);       
                userList.add(userDTO);
        }
        return userList;
    }

    @Transactional
    public void addUserToLibrary(AddUserRequestDTO addUserRequest) {
        User user = userRepository.findByMail(addUserRequest.getMail())
                .orElseThrow(() -> new IllegalArgumentException("User not found")) ;

        Library library = libraryRepository.findById(addUserRequest.getLibraryId())
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        userRoleRepository.findRoleByUserAndLibrary(user, library)
                .ifPresentOrElse(
                        existingRole -> {
                            existingRole.setRole(addUserRequest.getRole());
                            userRoleRepository.save(existingRole);
                        },
                        () -> userRoleRepository.save(new UserRole(user, library, addUserRequest.getRole()))
                );
    }

    @Transactional
    public void removeUserFromLibrary(int libraryId, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        userRoleRepository.findRoleByUserAndLibrary(user, library)
                .ifPresent(userRoleRepository::delete);
    }
}
