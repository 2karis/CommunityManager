package io.siliconsavannah.backend.controller;

import io.siliconsavannah.backend.dto.PaymentDto;
import io.siliconsavannah.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public PaymentDto processPayment(@RequestParam PaymentDto paymentDto) {
        return paymentService.processPayment(paymentDto);
    }
}
