package com.inventoryManagement.services;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CartService {
    Cart createCart(Cart cart);

    List<Item> getItemsInCart(Long cartId);

    void addItemToCart(Long cartId, Long itemId);

    Cart getCartById(Long id) throws ChangeSetPersister.NotFoundException;

    Cart addPaymentToCart(Long cartId, PaymentMethod payment) throws ChangeSetPersister.NotFoundException;

    List<Cart> getAllCarts();


    void calculateTotalPriceAndSavePayment(Long cartId);
}
