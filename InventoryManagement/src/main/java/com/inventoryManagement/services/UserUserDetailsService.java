package com.inventoryManagement.services;

import com.inventoryManagement.config.UserUserDetails;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findByName(username);
        return user.map(UserUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"+ username));
    }

}
