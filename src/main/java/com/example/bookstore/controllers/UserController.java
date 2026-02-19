package com.example.bookstore.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookstore.dtos.UserDto;
import com.example.bookstore.models.AppUser;
import com.example.bookstore.models.Role;
import com.example.bookstore.services.RoleService;
import com.example.bookstore.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;







@Controller
@AllArgsConstructor
public class UserController {
    private final BCryptPasswordEncoder passwordEncoder; 

    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleService roleService;
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    
}
@GetMapping("/admin/user")
public String userPage(Model model) {
    List<AppUser> users=userService.get();
    model.addAttribute("users", users);
    return "user/all";
}
@GetMapping("/admin/user/edit/{id}")
public String edit(@PathVariable Integer id,Model model) {
    AppUser user=userService.getUserById(id);
    List<Role> roles=roleService.all();
    Set<Role> userRoles=user.getRoles();
    model.addAttribute("roles", roles);
    model.addAttribute("userRoles", userRoles);
    model.addAttribute("userId", id);
    return "user/edit";
}
@GetMapping("/admin/user/edit/add/{uid}/{rid}")
public String addRoleToUser(@PathVariable Integer uid,@PathVariable Integer rid) {
    userService.addRoleToUser(uid, rid);
    return "redirect:/admin/user/edit/" + uid;
}
@GetMapping("/admin/user/edit/remove/{uid}/{rid}")
public String removeRoleToUser(@PathVariable Integer uid,@PathVariable Integer rid) {
    userService.removeRoleFromUser(uid, rid);
   return "redirect:/admin/user/edit/" + uid;
}

@GetMapping("/register")
public String registerPage(Model model) {
    model.addAttribute("userDto", new UserDto());
    return "user/register";
}

@PostMapping("/register")
public String register(
        @Valid @ModelAttribute("userDto") UserDto userDto,
        BindingResult result,
        Model model) {

    if (result.hasErrors()) {
        return "user/register";
    }

    AppUser user = new AppUser();
    user.setEmail(userDto.getEmail());
    user.setUsername(userDto.getUsername());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
Role userRole = roleService.find(9);

user.getRoles().add(userRole);

// save user with role attached
userService.add(user);
    return "redirect:/login";
}
}
