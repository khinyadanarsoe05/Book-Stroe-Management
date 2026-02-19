package com.example.bookstore.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.models.Book;
import com.example.bookstore.repos.BookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
@Autowired
private final BookRepo bookRepo;
public List<Book> all(){
    List<Book> books=bookRepo.findAll();
    return books;
}
public void create(Book book){
    bookRepo.save(book);
}
public void update(Book book){
    bookRepo.save(book);
}
public void drop(Long id){
    Book book=bookRepo.findById(id).orElse(null);
    bookRepo.delete(book);
}
public Book find(Long id){
    Book book=bookRepo.findById(id).orElse(null);
    return book;
}
}
