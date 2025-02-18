package br.com.pdv.order_api.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.*;

import br.com.pdv.order_api.infrastructure.controllers.request.PaymentRequest;
import org.junit.jupiter.api.Test;

public class PaymentRequestTest {

    @Test
    public void testPaymentRequestBuilder() {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(100.0)
                .order_id("order123")
                .method("credit_card")
                .status("paid")
                .payment_type("full")
                .build();

        assertNotNull(paymentRequest);
        assertEquals(100.0, paymentRequest.getAmount());
        assertEquals("order123", paymentRequest.getOrder_id());
        assertEquals("credit_card", paymentRequest.getMethod());
        assertEquals("paid", paymentRequest.getStatus());
        assertEquals("full", paymentRequest.getPayment_type());
    }

    @Test
    public void testSetterAndGetter() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setAmount(150.0);
        paymentRequest.setOrder_id("order456");
        paymentRequest.setMethod("paypal");
        paymentRequest.setStatus("pending");
        paymentRequest.setPayment_type("installment");

        assertEquals(150.0, paymentRequest.getAmount());
        assertEquals("order456", paymentRequest.getOrder_id());
        assertEquals("paypal", paymentRequest.getMethod());
        assertEquals("pending", paymentRequest.getStatus());
        assertEquals("installment", paymentRequest.getPayment_type());
    }

    @Test
    public void testToString() {
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(200.0)
                .order_id("order789")
                .method("bank_transfer")
                .status("completed")
                .payment_type("full")
                .build();

        String expectedToString = "PaymentRequest(amount=200.0, order_id=order789, method=bank_transfer, status=completed, payment_type=full)";
        assertEquals(expectedToString, paymentRequest.toString());
    }
}
