package com.checkout.paymentgateway.model;

import org.springframework.stereotype.Component;

@Component
public class PaymentDetails {
    private String transactionId;
    private String cardNumber;

    private Double originalAmount;
    private Double totalAmount;

    public PaymentDetails(){

    }
    public PaymentDetails(String transactionId, String cardNumber, Double originalAmount, Double totalAmount) {
        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.originalAmount = originalAmount;
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

