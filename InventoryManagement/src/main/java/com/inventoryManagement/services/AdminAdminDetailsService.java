package com.inventoryManagement.services;

//import com.inventoryManagement.config.AdminUserDetails;
//import com.inventoryManagement.entities.Admin;
//import com.inventoryManagement.repository.AdminRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class AdminAdminDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AdminRepo repo;
//    @Override
//    public AdminUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Admin> admin = repo.findByEmail(email);
//        return admin.map(AdminUserDetails::new)
//                .orElseThrow(()-> new UsernameNotFoundException("admin not found "+ email));
//    }
//
//}
