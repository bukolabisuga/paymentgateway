package com.checkout.paymentgateway.simulator;

import com.checkout.paymentgateway.model.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BankSimulator {

    public static double TRANSACTION_FEE = 0.02; // 2% transaction fee

    public boolean retrieveMoney(String cardNumber, double amount) {
        // Simulate card validation and processing
        if (isValidCard(cardNumber) && hasSufficientFunds(cardNumber, amount)) {
            // Deduct the transaction fee from the amount
            double fee = amount * TRANSACTION_FEE;
            double totalAmount = amount + fee;

            // Simulate money retrieval from the customer's card
            if (retrieveFromCard(cardNumber, totalAmount)) {
                // Transaction successful
                return true;
            }
        }

        return false;
    }

    private boolean isValidCard(String cardNumber) {
        // Check if the card number is valid or if it exists in a database
        return true;
    }

    private boolean hasSufficientFunds(String cardNumber, double amount) {
        // Check if the card has sufficient funds
        // Retrieve the available balance for the card from a database or a payment gateway
        double availableBalance = getAvailableBalance(cardNumber);
        return availableBalance >= amount;
    }

    private double getAvailableBalance(String cardNumber) {
        // Simulate the available balance as a double value
        return 1000.0; // Assume a fixed available balance for simplicity
    }

    private boolean retrieveFromCard(String cardNumber, double amount) {
        // Simulate retrieving money from the customer's card
        Random random = new Random();
        return random.nextBoolean(); // Simulate retrieval success/failure randomly
    }

    public PaymentResponse retrieveFromBank(String cardNumber, double amount) {
        BankSimulator simulator = new BankSimulator();

        PaymentResponse paymentResponse = new PaymentResponse();

        boolean transactionResult = simulator.retrieveMoney(cardNumber, amount);
        if (transactionResult) {
            paymentResponse.setMessage("Money retrieved successfully!");
            paymentResponse.setStatus("successful");
        } else {
            paymentResponse.setMessage("Failed to retrieve money. Please check your card details or available balance.");
            paymentResponse.setStatus("failed");
        }

        return paymentResponse;
    }
}
