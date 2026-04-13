package com.diaza.habitracker.services;

import com.diaza.habitracker.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDao userRepository; // Your JPA Repository

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.diaza.habitracker.entities.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER") // .roles("USER") automatically adds the "ROLE_" prefix
                .build();
    }
}