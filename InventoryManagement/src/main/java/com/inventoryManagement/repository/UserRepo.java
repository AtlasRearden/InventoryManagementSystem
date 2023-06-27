package com.inventoryManagement.repository;

import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User, Long>{

    @Query("SELECT p FROM Cart c JOIN c.paymentMethod p WHERE c.user = :user")
    List<PaymentMethod> getPaymentHistory(@Param("user") User user);

    Optional<User> findByName(String username);

}
