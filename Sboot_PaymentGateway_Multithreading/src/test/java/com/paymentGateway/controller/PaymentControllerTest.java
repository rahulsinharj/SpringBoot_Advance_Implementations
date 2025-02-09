package com.paymentGateway.controller;

import com.paymentGateway.entity.Payment;
import com.paymentGateway.kafkaUtil.PaymentProducer;
import com.paymentGateway.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Mock
    private PaymentProducer paymentProducer;

    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testProcessPayment() throws Exception {
        Payment payment = new Payment();
        payment.setCardNumber("1234567890123456");
        payment.setCardHolder("John Doe");
        payment.setAmount(100.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/processPayment")
                        .flashAttr("payment", payment))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    void testShowAllPayments() throws Exception {
        Model model = Mockito.mock(Model.class);
        List<Payment> payments = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setCardNumber("1111222233334444");
        payment1.setCardHolder("Alice");
        payment1.setAmount(50.0);

        Payment payment2 = new Payment();
        payment2.setCardNumber("5555666677778888");
        payment2.setCardHolder("Bob");
        payment2.setAmount(75.0);

        payments.add(payment1);
        payments.add(payment2);

        when(paymentService.getAllPayments()).thenReturn(payments);

        mockMvc.perform(MockMvcRequestBuilders.get("/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("showpage"))
                .andExpect(MockMvcResultMatchers.model().attribute("payments", payments));
    }

    // Add more test methods as needed for your controller
}
