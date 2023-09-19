package com.paymentGateway.controller;

import com.paymentGateway.entity.Payment;
import com.paymentGateway.kafkaUtil.PaymentProducer;
import com.paymentGateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentProducer paymentProducer;

    @RequestMapping({"/" , "/home"})
    public String home(Model model) {
        model.addAttribute("payment", new Payment());
        return "index";
    }

    @PostMapping("/processPayment")
    public String processPayment(@ModelAttribute Payment payment) {
        // Simulate payment processing
        paymentProducer.sendPaymentRequest(payment);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String showAllPayments(Model model) {
        System.out.println("*****ShowAllPayments*****");
        List<Payment> payments = paymentService.getAllPayments();
        System.out.println("*****Total Payments received : "+payments.size());
//        payments.forEach(System.out::println);

        model.addAttribute("payments", payments);
        return "showpage";
    }

}
