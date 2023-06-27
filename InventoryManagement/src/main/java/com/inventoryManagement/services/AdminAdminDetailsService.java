package com.inventoryManagement.services;

import com.inventoryManagement.config.AdminUserDetails;
import com.inventoryManagement.entities.Admin;
import com.inventoryManagement.repository.AdminRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AdminAdminDetailsService implements UserDetailsService {

    private AdminRepo repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = repo.findByEmail(email);
        return admin.map(AdminUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"+ email));
    }

}
