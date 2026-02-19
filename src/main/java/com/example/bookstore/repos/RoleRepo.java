package com.example.bookstore.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);

}
