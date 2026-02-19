package com.example.bookstore.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.Category;

@Repository
public interface CatRepo extends JpaRepository<Category,Integer> {

}
