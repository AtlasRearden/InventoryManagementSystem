package com.inventoryManagement.services;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();
    List<PaymentMethod> getPaymentHistory(Long id);
}
