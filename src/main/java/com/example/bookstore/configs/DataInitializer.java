package com.example.bookstore.configs;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.bookstore.models.AppUser;
import com.example.bookstore.models.Role;
import com.example.bookstore.repos.RoleRepo;
import com.example.bookstore.repos.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer {

    
    private final RoleRepo roleRepo;

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

@PostConstruct
public void init() {

    // 1️⃣ Create ADMIN role if not exist
       Role adminRole = roleRepo.findByName("ADMIN")
            .orElseGet(() -> {
                Role r = new Role();
                r.setName("ADMIN");
                return roleRepo.save(r);
            });

    // ✅ Create USER role if not exist
    Role userRole = roleRepo.findByName("USER")
            .orElseGet(() -> {
                Role r = new Role();
                r.setName("USER");
                return roleRepo.save(r);
            });
    // 2️⃣ Create admin user if not exist
    if (!userRepo.existsByEmail("admin@gmail.com")) {
        AppUser admin = new AppUser();
        admin.setUsername("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin123"));

        // Assign role properly
        admin.getRoles().add(adminRole); // only ADMIN role

        // Save user
        userRepo.save(admin);
    }
}

}