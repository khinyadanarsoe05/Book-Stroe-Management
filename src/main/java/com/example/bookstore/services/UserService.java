package com.example.bookstore.services;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.example.bookstore.configs.MyUserDetail;
import com.example.bookstore.models.AppUser;
import com.example.bookstore.models.Role;
import com.example.bookstore.repos.RoleRepo;
import com.example.bookstore.repos.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
@Autowired
private final UserRepo userRepo;
private final RoleRepo roleRepo;
    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(MyUserDetail::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));
    }
        

    
    public void add(AppUser user){
                 // encode here
        userRepo.save(user);
    }
    public List<AppUser> get(){
        return userRepo.findAll();
    }
    public AppUser getUserById(Integer id){
            return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void addRoleToUser(Integer uid, Integer rid) {
    AppUser user = getUserById(uid);
    Role role = roleRepo.findById(rid)
            .orElseThrow(() -> new RuntimeException("Role not found"));

    user.getRoles().add(role);
    userRepo.save(user);
}
public void removeRoleFromUser(Integer uid, Integer rid) {
    AppUser user = getUserById(uid);

    user.getRoles().removeIf(role -> role.getId().equals(rid));

    userRepo.save(user);
}

}
