package com.paymentGateway.service;

import com.paymentGateway.entity.Payment;
import com.paymentGateway.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void testProcessPaymentAsync() {
        Payment payment = new Payment();
        payment.setCardNumber("1234567890123456");
        payment.setCardHolder("John Doe");
        payment.setAmount(100.0);

        Payment processedPayment = new Payment();
        processedPayment.setId(1L);

        when(paymentRepository.save(payment)).thenReturn(processedPayment);

        Payment result = paymentService.processPaymentAsync(payment).join();

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Payment> payments = paymentService.getAllPayments();

        assertThat(payments).isEmpty();
    }

    // Add more tests as needed for your service
}
