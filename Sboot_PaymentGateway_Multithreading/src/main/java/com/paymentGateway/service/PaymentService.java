package com.paymentGateway.service;

import com.paymentGateway.entity.Payment;
import com.paymentGateway.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Async
    public CompletableFuture<Payment> processPaymentAsync(Payment payment) {
        // Simulate payment processing
        Payment processedPayment = paymentRepository.save(payment);
        return CompletableFuture.completedFuture(processedPayment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
