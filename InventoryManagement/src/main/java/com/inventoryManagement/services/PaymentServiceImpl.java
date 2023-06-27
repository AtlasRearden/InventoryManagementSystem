package com.inventoryManagement.services;

import com.inventoryManagement.entities.PaymentMethod;
import com.inventoryManagement.exceptions.CartNotFoundException;
import com.inventoryManagement.repository.CartRepo;
import com.inventoryManagement.repository.PaymentMethodRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentMethodRepo paymentMethodRepo;

    @Autowired
    CartRepo cartRepo;

    @Override
    public PaymentMethod createPayment(PaymentMethod payment) {
        return paymentMethodRepo.save(payment);
    }

    @Override
    public List<PaymentMethod> getAllPayments() {
        return paymentMethodRepo.findAll();
    }

    @Override
    public void deletePayment(Long id){
        paymentMethodRepo.deleteById(id);
    }

    @Override
    public PaymentMethod getPaymentById(Long id) throws ChangeSetPersister.NotFoundException {
        return paymentMethodRepo.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public PaymentMethod updatePayment(PaymentMethod payment) {
        return paymentMethodRepo.save(payment);
    }



}
