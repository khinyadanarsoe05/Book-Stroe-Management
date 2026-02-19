package com.example.bookstore.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import com.example.bookstore.dtos.BookDto;
import com.example.bookstore.models.Book;
import com.example.bookstore.models.Category;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CatService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
@AllArgsConstructor

public class BookController {
@Autowired
private final BookService bookService;
@Autowired
private final CatService catService;
private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/";
@GetMapping("/book")
public String all(Model model) {
   List<Book> books= bookService.all();
   List<Category> categories = catService.all();
   model.addAttribute("books", books);
   model.addAttribute("categories", categories);
    return "book/all";
}
@GetMapping("/admin/book/add")
public String add(Model model) {
    List<Category> categories = catService.all();
     model.addAttribute("categories", categories);
    return "book/add";
}
public String saveFile(MultipartFile file){
    try {
        Path upload_Path=Paths.get(UPLOAD_DIR);
        if(!Files.exists(upload_Path)){
            Files.createDirectories(upload_Path);
        }
        String fileName=System.currentTimeMillis()+"-"+file.getOriginalFilename();
        Path filePath=upload_Path.resolve(fileName);
        Files.copy(file.getInputStream(),filePath);
        return fileName;
    } catch (IOException e) {
        System.out.println("Error Saving File" + e.getMessage());
        return null;
    }
}
@PostMapping("/admin/book/add")
public String create(@ModelAttribute BookDto bookDto) {
     Book book=new Book();
     if(!bookDto.getCoverImage().isEmpty()){
        String fileName=saveFile(bookDto.getCoverImage());
        book.setCoverImageUrl(fileName);
     }
     book.setTitle(bookDto.getTitle());
     book.setAuthor(bookDto.getAuthor());
     book.setPublicationDate(bookDto.getPublicationDate());
     book.setPrice(bookDto.getPrice());
     book.setStock(bookDto.getStock());
     book.setDescription(bookDto.getDescription());
     Category category =catService.find(bookDto.getCategoryId());
     book.setCategory(category);
     bookService.create(book);
    return "redirect:/book";
}
@GetMapping("/admin/book/edit/{id}")
public String edit(Model model,@PathVariable Long id) {
   Book book= bookService.find(id);
   model.addAttribute("book", book);
   List<Category> categories = catService.all();
     model.addAttribute("categories", categories);
    return "book/edit";
}
@PostMapping("/admin/book/edit/{id}")
public String update(@PathVariable Long id,@ModelAttribute BookDto bookDto) {
    Book book=bookService.find(id);
     book.setTitle(bookDto.getTitle());
     book.setAuthor(bookDto.getAuthor());
     book.setPublicationDate(bookDto.getPublicationDate());
     book.setDescription(bookDto.getDescription());
     book.setPrice(bookDto.getPrice());
     book.setStock(bookDto.getStock());
     Category category =catService.find(bookDto.getCategoryId());
     book.setCategory(category);
     if (bookDto.getCoverImage() != null && !bookDto.getCoverImage().isEmpty()) {
    String fileName = saveFile(bookDto.getCoverImage());
    book.setCoverImageUrl(fileName);
}

    bookService.update(book);
    return "redirect:/book";
}
@GetMapping("/admin/book/drop/{id}")
public String drop(@PathVariable Long id) {
    bookService.drop(id);
    return "redirect:/book";
}
@GetMapping("/book/filter/{id}")
public String filterBook(@PathVariable Integer id,Model model) {
    Category category=catService.find(id);
    Set<Book> books=category.getBooks();
    model.addAttribute("books", books);
    List<Category> categories=catService.all();
    model.addAttribute("categories", categories);

    return "book/all";
}


}
