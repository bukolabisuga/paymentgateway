package com.checkout.paymentgateway.model;

import org.springframework.stereotype.Component;

@Component
public class PaymentRequest {
    private String cardNumber;
    private Double amount;
    private String cvv;
    private String expiry;
    private String currency;

    public PaymentRequest() {
    }

    public PaymentRequest(String cardNumber, Double amount, String cvv, String expiry, String currency) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cvv = cvv;
        this.expiry = expiry;
        this.currency = currency;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Double getAmount() { return amount; }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
