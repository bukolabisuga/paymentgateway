package com.checkout.paymentgateway;

import com.checkout.paymentgateway.controller.PaymentController;
import com.checkout.paymentgateway.exception.PaymentDetailsNotFoundException;
import com.checkout.paymentgateway.model.PaymentDetails;
import com.checkout.paymentgateway.service.PaymentTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PaymentTransactionServiceTest {
    @Mock
    private PaymentTransactionService paymentTransactionService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPaymentDetails_ValidTransactionId_ReturnsPaymentDetails() {
        // Arrange
        String transactionId = "abc123";
        PaymentDetails expectedPaymentDetails = new PaymentDetails(transactionId, "1234acc", 100.0, 100.2);
        when(paymentTransactionService.getPaymentDetails(transactionId)).thenReturn(expectedPaymentDetails);

        // Act
        PaymentDetails response = paymentController.getPaymentDetails(transactionId);

        // Assert
        assertEquals(expectedPaymentDetails, response);
        verify(paymentTransactionService, times(1)).getPaymentDetails(transactionId);
    }

    @Test
    void getPaymentDetails_InvalidTransactionId_ThrowsPaymentDetailsNotFoundException() {
        // Arrange
        String transactionId = "xyz789";
        when(paymentTransactionService.getPaymentDetails(transactionId)).thenThrow(PaymentDetailsNotFoundException.class);

        // Act and Assert
        assertThrows(PaymentDetailsNotFoundException.class, () -> paymentController.getPaymentDetails(transactionId));
        verify(paymentTransactionService, times(1)).getPaymentDetails(transactionId);
    }
}
