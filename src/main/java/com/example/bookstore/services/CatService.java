package com.example.bookstore.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.bookstore.models.Category;
import com.example.bookstore.repos.CatRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatService {
@Autowired
private final CatRepo catRepo;
public List<Category> all(){
    List<Category> categories=catRepo.findAll();
    return categories;
}
public Category find(Integer id){
    Category category=catRepo.findById(id).orElseThrow(() -> new RuntimeException("No Category with that id")) ;
    return category;
}
public void create(String name){
    Category category=new Category();
    category.setName(name);
    catRepo.save(category);
}
public void update(Integer id,String name){
    Category category=catRepo.findById(id).orElseThrow(() -> new RuntimeException("No Category with that id")) ;
    category.setName(name);
    catRepo.save(category);
}
public void drop(Integer id){
    Category category=catRepo.findById(id).orElseThrow(() -> new RuntimeException("No Category with that id"));
    catRepo.delete(category);
}
}
