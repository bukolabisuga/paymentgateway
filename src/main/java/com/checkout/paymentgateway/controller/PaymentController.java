package com.checkout.paymentgateway.controller;

import com.checkout.paymentgateway.exception.PaymentDetailsNotFoundException;
import com.checkout.paymentgateway.model.PaymentDetails;
import com.checkout.paymentgateway.model.PaymentRequest;
import com.checkout.paymentgateway.model.PaymentResponse;
import com.checkout.paymentgateway.service.PaymentService;
import com.checkout.paymentgateway.service.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentTransactionService paymentTransactionService;

    @GetMapping("/")
    public String index() {
        return "Welcome to Checkout!";
    }

    // Processes a payment via the bank simulator
    @PostMapping("/process")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {

        return paymentService.processPayment(paymentRequest);

    }

    // Gets the payment details of a specified transaction
    @GetMapping("/{transactionId}")
    @ExceptionHandler(PaymentDetailsNotFoundException.class)
    public PaymentDetails getPaymentDetails(@PathVariable String transactionId) throws PaymentDetailsNotFoundException {
        PaymentDetails paymentDetails = paymentTransactionService.getPaymentDetails(transactionId);
        if (paymentDetails != null) {
            return paymentDetails;
        } else {
            throw new PaymentDetailsNotFoundException("woza");
        }
    }
}
