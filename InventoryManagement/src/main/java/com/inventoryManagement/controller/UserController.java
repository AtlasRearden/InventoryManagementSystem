package com.inventoryManagement.controller;

import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.UserRepo;
import com.inventoryManagement.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final UserRepo userRepo;

    @GetMapping("/{id}/get")
    public List<PaymentMethod> getPaymentHistory(@PathVariable Long id){
        return userService.getPaymentHistory(id);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/admin")
    public User createAdmin(@RequestBody User adminUser){
        return userService.createAdmin(adminUser);
    }

    @PostMapping("/{adminUser}/usr")
    public ResponseEntity<User> createUserByAdmin(@PathVariable User adminUser, @RequestBody User newUser) {
        User createdUser = userService.createUserByAdmin(adminUser, newUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable Long id){
       userService.deleteUser(id);
    }

    @GetMapping("/adminEmail")
    public ResponseEntity<User> findAdminByEmail(@RequestParam("email") String email) {
        User adminUsers = userService.findByEmailAndIsAdmin(email, true);
        return ResponseEntity.ok(adminUsers);
    }


}
