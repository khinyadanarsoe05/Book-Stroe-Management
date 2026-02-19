package com.example.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookstore.models.Category;
import com.example.bookstore.services.CatService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@AllArgsConstructor
@RequestMapping("/admin/category")
public class CatController {
@Autowired
private final CatService catService;
@GetMapping("")
public String all(Model model){
    List<Category> categories=catService.all();
    model.addAttribute("categories",categories);
    return "category/all";
}
@GetMapping("/add")
public String add(){
    return "category/add";
}
@PostMapping("/add")
public String create(@RequestParam String name) {
    catService.create(name);
    return "redirect:/admin/category";
}
@GetMapping("/edit/{id}")
public String edit(@PathVariable Integer id,Model model){
    Category category=catService.find(id);
    model.addAttribute("category", category);
    return "category/edit";
}
@PostMapping("/edit/{id}")
public String update(@PathVariable Integer id,@RequestParam String name) {
           catService.update(id, name);    
    return "redirect:/admin/category";
}

@GetMapping("/drop/{id}")
public String drip(@PathVariable Integer id) {
    catService.drop(id);
    return "redirect:/admin/category";
}

}



