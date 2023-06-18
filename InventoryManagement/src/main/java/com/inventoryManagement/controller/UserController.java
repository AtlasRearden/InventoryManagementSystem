package com.inventoryManagement.controller;


import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.UserRepo;
import com.inventoryManagement.services.PaymentServiceImpl;
import com.inventoryManagement.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/disusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
