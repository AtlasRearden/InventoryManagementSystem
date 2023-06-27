package com.inventoryManagement.auth;

import com.inventoryManagement.config.JwtHelper;
import com.inventoryManagement.config.JwtRequest;
import com.inventoryManagement.config.JwtResponse;
import com.inventoryManagement.config.UserUserDetails;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.UserRepo;
import com.inventoryManagement.services.UserUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtHelper helper;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        String username = request.getName();
        String password = request.getPassword();

        User user = userRepo.findByName(username).orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            UserUserDetails userDetails = (UserUserDetails) userDetailsService.loadUserByUsername(username);
            String token = helper.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .name(userDetails.getUsername()).build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        throw new BadCredentialsException("Invalid email or password");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
