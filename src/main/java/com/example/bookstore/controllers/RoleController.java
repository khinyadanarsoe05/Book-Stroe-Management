package com.example.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.bookstore.models.Role;
import com.example.bookstore.services.RoleService;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@AllArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {
@Autowired
private final RoleService roleService;
@GetMapping("")
public String all(Model model) {
   List<Role> roles= roleService.all();
   model.addAttribute("roles", roles);
    return "role/all";
}
@GetMapping("/add")
public String add() {
    return "role/add";
}
@PostMapping("/add")
public String create(@RequestParam String name) {
    roleService.add(name);
    return "redirect:/admin/roles";
}
@GetMapping("/edit/{id}")
public String edit(@PathVariable Integer id,Model model) {
    Role role=roleService.find(id);
    model.addAttribute("role", role);
    return "role/edit";
}
@PostMapping("/edit/{id}")
public String update(@PathVariable Integer id,@RequestParam String name) {
    roleService.edit(id, name); 
    return "redirect:/admin/roles";
}
@GetMapping("/drop/{id}")
public String drop(@PathVariable Integer id) {
    roleService.drop(id);
    return "redirect:/admin/roles";
}




}
