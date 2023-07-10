package com.inventoryManagement.auth;

import com.inventoryManagement.config.*;
import com.inventoryManagement.dto.*;
//import com.inventoryManagement.entities.Admin;
import com.inventoryManagement.entities.User;
//import com.inventoryManagement.repository.AdminRepo;
import com.inventoryManagement.repository.UserRepo;
//import com.inventoryManagement.services.AdminAdminDetailsService;
//import com.inventoryManagement.services.AuthenticationService;
import com.inventoryManagement.services.AuthenticationService;
import com.inventoryManagement.services.UserService;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserUserDetailsService userDetailsService;
//
////    @Autowired
////    private AdminAdminDetailsService adminDetailsService;
////
////    @Autowired
////    private AdminRepo adminRepo;
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private JwtHelper helper;
//    private Logger logger = LoggerFactory.getLogger(AuthController.class);
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public AuthController(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
////    @Autowired
////    AuthenticationService service;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//        String username = request.getName();
////        String email=request.getEmail();
//        String password = request.getPassword();
//
//        User user = userRepo.findByName(username).orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
//
//        if (passwordEncoder.matches(password, user.getPassword())) {
//            UserUserDetails userDetails = (UserUserDetails) userDetailsService.loadUserByUsername(username);
//            String token = helper.generateToken(userDetails);
//
//
//
//            JwtResponse response = JwtResponse.builder()
//                    .jwtToken(token)
//                    .name(userDetails.getUsername()).build();
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//
//        throw new BadCredentialsException("Invalid name or password");
//    }
//
////    @PostMapping("/admin/login")
////    public ResponseEntity<AdminResponseDto> adminLogin(@RequestBody AdminRequestDto request) {
////        String email = request.getEmail();
////        String password = request.getPassword();
////
////        Admin admin = adminRepo.findByEmail(email)
////                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
////
////        if (passwordEncoder.matches(password, admin.getPassword())) {
////            AdminUserDetails adminUserDetails =  adminDetailsService.loadUserByUsername(email);
////            String token = helper.generateToken(adminUserDetails);
////
////            AdminResponseDto adminResponseDto = AdminResponseDto.builder()
////                    .jwtToken(token)
////                    .email(adminUserDetails.getUsername())
////                    .build();
////
////            return new ResponseEntity<>(adminResponseDto, HttpStatus.OK);
////        }
////
////        throw new BadCredentialsException("Invalid email or password");
////    }
//
////    @PostMapping("/register")
////    public ResponseEntity<AuthenticationResponse> register(
////            @RequestBody RegisterRequest request
////    ){
////        return ResponseEntity.ok(service.register(request));
////    }
////
////    @PostMapping("/authenticate")
////    public ResponseEntity<AuthenticationResponse> authenticate(
////            @RequestBody AuthenticateRequest request
////    ){
////        return ResponseEntity.ok(service.authenticate(request));
////    }
//
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//    }
//}


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtHelper jwtHelper;
    @Autowired
    private AuthenticationService service;

    public AuthController(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest Request) {
        try {
            // Authenticate user
            User user = service.authenticateUser(Request.getEmail(), Request.getPassword());
            if (user != null) {
                // Generate token for user
                String token = jwtHelper.generateToken(user.getEmail(), user.isAdmin());
                JwtResponse response = JwtResponse.builder()
                        .jwtToken(token)
                        .email(user.getName()).build();

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody JwtRequest Request) {
        try {
            // Authenticate admin
            User admin = service.authenticateAdmin(Request.getEmail(), Request.getPassword());
            if (admin != null) {
                // Generate token for admin
                String token = jwtHelper.generateToken(admin.getEmail(), admin.isAdmin());
                JwtResponse response = JwtResponse.builder()
                        .jwtToken(token)
                        .email(admin.getName()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }
}