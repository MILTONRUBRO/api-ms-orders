package br.com.pdv.order_api.infrastructure.controllers.response;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PaymentResponseTest {

    @Test
    public void testPaymentResponseConstructor() {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setUuid("123e4567-e89b-12d3-a456-426614174000");

        assertEquals("123e4567-e89b-12d3-a456-426614174000", paymentResponse.getUuid());
    }

    @Test
    public void testPaymentResponseBuilder() {
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .uuid("123e4567-e89b-12d3-a456-426614174001")
                .build();

        assertNotNull(paymentResponse);
        assertEquals("123e4567-e89b-12d3-a456-426614174001", paymentResponse.getUuid());
    }

    @Test
    public void testToString() {
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .uuid("123e4567-e89b-12d3-a456-426614174002")
                .build();

        String expectedToString = "PaymentResponse(uuid=123e4567-e89b-12d3-a456-426614174002)";
        assertEquals(expectedToString, paymentResponse.toString());
    }
}
