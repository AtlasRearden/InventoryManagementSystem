package com.inventoryManagement.controller;

import com.inventoryManagement.entities.Cart;
import com.inventoryManagement.entities.Item;
import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.services.CartServiceImpl;
import com.inventoryManagement.services.ItemServiceImpl;
import com.inventoryManagement.services.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    @Autowired
    private final CartServiceImpl cartService;

    @Autowired
    private final ItemServiceImpl itemService;

    @Autowired
    private final PaymentServiceImpl paymentService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart= cartService.createCart(cart);
        return ResponseEntity.ok(createdCart);
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return cartService.getCartById(id);
    }

    @PostMapping("/{cartId}/payments")
    public Cart addPaymentToCart(@PathVariable Long cartId, @RequestBody PaymentMethod payment) throws ChangeSetPersister.NotFoundException {
        return cartService.addPaymentToCart(cartId, payment);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<Item> addItemToCart(@PathVariable Long cartId, @RequestBody Long itemId){
        cartService.addItemToCart(cartId,itemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/tp")
    public ResponseEntity<String> calculateTotalPriceAndSavePayment(@PathVariable Long cartId) {
        cartService.calculateTotalPriceAndSavePayment(cartId);
        return ResponseEntity.ok("Total price calculated and payment updated successfully");
    }

}