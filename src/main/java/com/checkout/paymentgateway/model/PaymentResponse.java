package com.checkout.paymentgateway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@Component
public class PaymentResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double originalAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalAmount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currency;

    public PaymentResponse() {

    }

    public PaymentResponse(String message, Double originalAmount, String status, Double totalAmount, String transactionId, String currency) {
        this.message = message;
        this.originalAmount = originalAmount;
        this.status = status;
        this.totalAmount = totalAmount;
        this.transactionId = transactionId;
        this.currency = currency;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() { return transactionId; }

    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }


    public Double getOriginalAmount() { return originalAmount; }

    public void setOriginalAmount(Double originalAmount) { this.originalAmount = originalAmount; }

    public Double getTotalAmount() { return totalAmount; }

    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}

