package com.checkout.paymentgateway.service;

import com.checkout.paymentgateway.model.PaymentDetails;
import com.checkout.paymentgateway.model.PaymentRequest;
import com.checkout.paymentgateway.model.PaymentResponse;
import com.checkout.paymentgateway.simulator.BankSimulator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final BankSimulator bankSimulator;
    private final PaymentTransactionService paymentTransactionService;

    public PaymentService(PaymentTransactionService paymentTransactionService) {
        bankSimulator = new BankSimulator();
        this.paymentTransactionService = paymentTransactionService;
    }

    // Generate random transaction ID
    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        String transactionId = generateTransactionId();

        if(!validatePaymentRequest(request)) {
            response.setStatus("failed");
            response.setMessage("Invalid payment request.");
            return response;
        }

        Double totalAmount = request.getAmount() + BankSimulator.TRANSACTION_FEE;


        // Checks if the transaction is a duplicate to avoid processing it twice
        if (paymentTransactionService.isTransactionDuplicate(transactionId, request.getCardNumber(), request.getAmount())) {
            response.setMessage("Duplicate transaction.");
            response.setStatus("Duplicate transaction: " + transactionId);

            return response;
        }

        // Processes the payment through the bank simulator
        response = bankSimulator.retrieveFromBank(request.getCardNumber(), request.getAmount());
        response.setOriginalAmount(request.getAmount());
        response.setTransactionId(transactionId);
        response.setTotalAmount(totalAmount);
        response.setCurrency(request.getCurrency());

        // Store the transaction
        PaymentDetails paymentDetails = new PaymentDetails(transactionId, request.getCardNumber(), request.getAmount(), totalAmount);
        paymentTransactionService.storePaymentDetails(paymentDetails);

        return response;
    }

    public boolean validatePaymentRequest(PaymentRequest request) {
        if (request == null) {
            return false;
        }

        return request.getCardNumber() != null && request.getAmount() != null && request.getCurrency() != null && request.getCvv() != null && request.getExpiry() != null;
    }
}


