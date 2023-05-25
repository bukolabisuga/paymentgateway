package com.checkout.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaymentDetailsNotFoundException extends RuntimeException  {
    public PaymentDetailsNotFoundException(String message) {
        super(message);
    }
}
