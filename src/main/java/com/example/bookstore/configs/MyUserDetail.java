package com.example.bookstore.configs;
import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.bookstore.models.AppUser;

public class MyUserDetail implements UserDetails {
private AppUser user;
public MyUserDetail (AppUser user){
    this.user=user;
}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    //     Set<Role> roles=user.getRoles();
    //     List<SimpleGrantedAuthority> authorities=new ArrayList<>();
    //    for (Role role : roles) {
    //     authorities.add(new SimpleGrantedAuthority(role.getName()));
           
    // }
    //  return authorities;
    return user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .toList();
    }

    @Override
    public  String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
   
    public Integer getId() {
        return user.getId();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
