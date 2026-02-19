package com.example.bookstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long>{

}
