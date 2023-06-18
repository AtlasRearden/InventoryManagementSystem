package com.inventoryManagement.controller;


import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.services.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private final PaymentServiceImpl paymentService;

    @GetMapping("/payments")
    public List<PaymentMethod> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/payments")
    public ResponseEntity<?> createPayment(@RequestBody PaymentMethod payment) {
        paymentService.createPayment(payment);
        return ResponseEntity.ok("Payment created successfully");
    }

    @GetMapping("/{id}")
    public PaymentMethod getPaymentById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return paymentService.getPaymentById(id);
    }

//    @PostMapping("/payments")
//    public PaymentMethod createPayment(@RequestBody PaymentMethod payment) {
//        return paymentService.createPayment(payment);
//    }

    @PutMapping("/{id}")
    public PaymentMethod updatePayment(@RequestBody PaymentMethod payment) {
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentMethod> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }

}
