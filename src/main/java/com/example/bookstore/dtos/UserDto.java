package com.example.bookstore.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

@NotBlank(message = "User Name is required")
@Size(min = 3,max = 30,message = "User name must be between 3 and 30")
private String username;
@NotBlank(message = "Email Address is required")
@Size(min = 3,max = 30,message = "Email must be between 3 and 30")
private String email;
@NotBlank(message = "Password is required")
@Size(min = 6,max = 30,message = "Password must be between 3 and 30")
private String password;
}
