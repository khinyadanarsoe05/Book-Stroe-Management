package com.example.bookstore.models;


import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
private Integer id;
private String username;
private String email;
private String password;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name="users_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
private Set<Role> roles=new HashSet<>();
}