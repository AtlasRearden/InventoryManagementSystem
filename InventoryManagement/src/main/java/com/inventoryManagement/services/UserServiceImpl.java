package com.inventoryManagement.services;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.repository.CartRepo;
import com.inventoryManagement.repository.PaymentMethodRepo;
import com.inventoryManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PaymentMethodRepo paymentMethodRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartService cartService;

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<PaymentMethod> getPaymentHistory(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        List<PaymentMethod> paymentHistory = new ArrayList<>();

        for (Cart cart : user.getCarts()) {
            if (cart.getPaymentMethod() != null) {
                paymentHistory.add(cart.getPaymentMethod());
            }
        }
        return paymentHistory;

    }


    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
