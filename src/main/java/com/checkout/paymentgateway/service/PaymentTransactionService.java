package com.checkout.paymentgateway.service;

import com.checkout.paymentgateway.exception.PaymentDetailsNotFoundException;
import com.checkout.paymentgateway.model.PaymentDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentTransactionService {
    private Map<String, PaymentDetails> paymentDetailsMap;

    public PaymentTransactionService() {
        paymentDetailsMap = new HashMap<>();
    }

    // Store the payment details
    public void storePaymentDetails(PaymentDetails paymentDetails) {
        paymentDetailsMap.put(paymentDetails.getTransactionId(), paymentDetails);
    }

    // Get the payment details
    public PaymentDetails getPaymentDetails(String transactionId) throws PaymentDetailsNotFoundException {
        if (paymentDetailsMap.containsKey(transactionId)) {
            return paymentDetailsMap.get(transactionId);
        } else {
            throw new PaymentDetailsNotFoundException("Payment details not found for transaction : " + transactionId);
        }
    }

    // Check for duplicates
    public boolean isTransactionDuplicate(String transactionId, String cardNumber, double amount) {
        for (PaymentDetails paymentDetails : paymentDetailsMap.values()) {
            if (paymentDetails.getTransactionId().equals(transactionId) &&
                    paymentDetails.getCardNumber().equals(cardNumber) &&
                    paymentDetails.getOriginalAmount() == amount) {
                return true;
            }
        }

        return false;
    }
}
