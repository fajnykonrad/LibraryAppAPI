package com.example.library_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library_api.model.Library;
import com.example.library_api.model.Role;
import com.example.library_api.model.User;
import com.example.library_api.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT ur.user FROM UserRole ur WHERE ur.library = :library")
    List<User> findUsersInLibrary(@Param("library") Library library);

    @Query("SELECT ur.user FROM UserRole ur WHERE ur.library = :library AND ur.role = :role")
    List<User> findUsersByLibraryAndRole(@Param("library") Library library, @Param("role") Role role);

    Optional<UserRole> findByUserAndLibrary(User user, Library library);
}
