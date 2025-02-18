package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentMethodTest {

	@Test
	void testPIX() {
		PaymentMethod paymentMethod = PaymentMethod.PIX;
		assertEquals("PIX", paymentMethod.getPaymentType());
	}

	@Test
	void testQRCode() {
		PaymentMethod paymentMethod = PaymentMethod.QR_CODE;
		assertEquals("QR Code", paymentMethod.getPaymentType());
	}

	@Test
	void testCash() {
		PaymentMethod paymentMethod = PaymentMethod.CASH;
		assertEquals("Dinheiro", paymentMethod.getPaymentType());
	}

	@Test
	void testCreditCard() {
		PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
		assertEquals("Cartão de Crédito", paymentMethod.getPaymentType());
	}
}
