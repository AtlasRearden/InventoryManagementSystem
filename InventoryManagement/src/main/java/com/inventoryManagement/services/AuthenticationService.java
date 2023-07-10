package com.inventoryManagement.services;

import com.inventoryManagement.auth.Role;
import com.inventoryManagement.config.JwtHelper;
import com.inventoryManagement.dto.AuthenticateRequest;
import com.inventoryManagement.dto.AuthenticationResponse;
import com.inventoryManagement.dto.RegisterRequest;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final UserRepo userRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtHelper jwtHelper;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .roles(Role.USER)
//                .build();
//        userRepo.save(user);
//        var jwtToken = jwtHelper.generateToken((UserDetails) user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticateRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
//        var jwtToken = jwtHelper.generateToken((UserDetails) user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
//    private final AuthenticationManager authenticationManager;

    public User authenticateUser(String email, String password) {
        User user = userRepo.findByEmailAndIsAdmin(email, false);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return  user;
        }
        return null;
    }

    public User authenticateAdmin(String email, String password) {
        User admin = userRepo.findByEmailAndIsAdmin(email, true);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }
}