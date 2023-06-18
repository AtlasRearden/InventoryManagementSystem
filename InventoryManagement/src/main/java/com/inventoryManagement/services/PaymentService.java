package com.inventoryManagement.services;

import com.inventoryManagement.entities.PaymentMethod;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PaymentService {


    PaymentMethod createPayment(PaymentMethod payment);

    List<PaymentMethod> getAllPayments();


    void deletePayment(Long id);

    PaymentMethod getPaymentById(Long id) throws ChangeSetPersister.NotFoundException;

    PaymentMethod updatePayment(PaymentMethod payment);
}
