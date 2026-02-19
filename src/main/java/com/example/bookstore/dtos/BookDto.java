package com.example.bookstore.dtos;





import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    @NotEmpty(message = "Title can't be empty")
private String title;
private String author;
private double price;
private int stock;
private String description;
private MultipartFile coverImage;
private String publicationDate;
private Integer categoryId;
}
