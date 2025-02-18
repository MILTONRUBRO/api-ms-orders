package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PaymentEntityTest {

    @Test
    public void testPaymentEntityConstructor() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);

        PaymentEntity payment = new PaymentEntity();
        payment.setId(1L);
        payment.setAmount(100.0);
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setOrder(order);

        assertEquals(1L, payment.getId());
        assertEquals(100.0, payment.getAmount());
        assertEquals(PaymentMethod.CREDIT_CARD, payment.getPaymentMethod());
        assertEquals(1L, payment.getOrder().getId());
    }

    @Test
    public void testToString() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);

        PaymentEntity payment = new PaymentEntity();
        payment.setId(1L);
        payment.setAmount(100.0);
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setOrder(order);

        String expectedToString = "PaymentEntity(id=1, amount=100.0, paymentMethod=CREDIT_CARD, order=OrderEntity{id=1})";
    }

    @Test
    public void testSettersAndGetters() {
        OrderEntity order = new OrderEntity();
        order.setId(2L);

        PaymentEntity payment = new PaymentEntity();

        payment.setId(2L);
        payment.setAmount(200.0);
        payment.setPaymentMethod(PaymentMethod.PIX);
        payment.setOrder(order);

        assertEquals(2L, payment.getId());
        assertEquals(200.0, payment.getAmount());
        assertEquals(PaymentMethod.PIX, payment.getPaymentMethod());
        assertEquals(2L, payment.getOrder().getId());
    }
}
