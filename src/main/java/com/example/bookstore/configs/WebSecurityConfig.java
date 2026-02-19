package com.example.bookstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.bookstore.services.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
   provider.setUserDetailsService(userDetailsService()); // ✅ use your UserService
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
}

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .formLogin(httpForm->{
                    httpForm.loginPage("/login").permitAll();
                    httpForm.defaultSuccessUrl("/", true);
                })
                .logout(logout -> logout
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login?logout")
    .invalidateHttpSession(true)
    .clearAuthentication(true)
    .deleteCookies("JSESSIONID")
    .permitAll()
)

                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/login","/register","/css/**","/images/**").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/book/**").hasAnyRole("ADMIN","USER");
                    registry.anyRequest().authenticated();
                })
        .build();
    }
}
