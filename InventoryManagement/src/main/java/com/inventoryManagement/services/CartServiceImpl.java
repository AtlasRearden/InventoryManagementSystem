package com.inventoryManagement.services;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.repository.CartRepo;
import com.inventoryManagement.repository.ItemRepo;
import com.inventoryManagement.repository.PaymentMethodRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    @Autowired
    private final CartRepo cartRepo;

    @Autowired
    private final ItemRepo itemRepo;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private final PaymentMethodRepo paymentMethodRepo;


    @Override
    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public List<Item> getItemsInCart(Long cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        return cart.getItems();
    }

//    @Override
//    public Item addItemToCart(Long cartId, Item item) {
//        Cart cart= cartRepo.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
//        item.setCart(cart);
//        return itemRepo.save(item);
//    }

    @Override
    public Cart getCartById(Long id) throws ChangeSetPersister.NotFoundException {
        return cartRepo.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Cart addPaymentToCart(Long cartId, PaymentMethod payment) throws ChangeSetPersister.NotFoundException {
        Cart cart = getCartById(cartId);
        payment.setCart(cart);
        paymentService.createPayment(payment);
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }


    public void addItemToCart(Long cartId, Long itemId) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);

        if (cart != null && item != null) {
            itemRepo.save(item);
            cart.getItems().add(item);
            cartRepo.save(cart);
        }
    }


    @Override
    public void calculateTotalPriceAndSavePayment(Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        double total_price = 0.0;
        for (Item item : cart.getItems()) {
            total_price += item.getPrice();
        }

        PaymentMethod payment = cart.getPaymentMethod();
        if (payment == null) {
            payment = new PaymentMethod();
            cart.setPaymentMethod(payment);
        }

        payment.setTotal_price(BigDecimal.valueOf(total_price));
        payment.setPayment_date(Date.valueOf((LocalDate.now())));
        payment.setCart(cart);
        paymentMethodRepo.save(payment);
    }

}
