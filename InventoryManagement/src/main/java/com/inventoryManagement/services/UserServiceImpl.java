package com.inventoryManagement.services;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;
import com.inventoryManagement.exceptions.UnauthorizedException;
import com.inventoryManagement.repository.CartRepo;
import com.inventoryManagement.repository.PaymentMethodRepo;
import com.inventoryManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public User findByEmailAndIsAdmin(String email, boolean isAdmin) {
        User adminUser = userRepo.findByEmailAndIsAdmin(email,isAdmin);
        return adminUser;
    }

    @Override
    public User createUserByAdmin(User adminUser, User newUser) {
        if (!adminUser.isAdmin()) {
            throw new UnauthorizedException("Only admin can create new users.");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepo.save(newUser);
    }

    @Override
    public User createAdmin(User adminUser) {
        if (!adminUser.isAdmin()) {
            throw new UnauthorizedException("Only admin can create new users.");
        }

        // Encrypt the password before saving
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));

        return userRepo.save(adminUser);
    }



    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
