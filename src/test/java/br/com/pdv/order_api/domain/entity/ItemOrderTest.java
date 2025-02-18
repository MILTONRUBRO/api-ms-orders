package br.com.pdv.order_api.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemOrderTest {

	@Test
	void testItemOrderCreation() {
		Long id = 1L;
		Long orderId = 2L;
		Long productId = 3L;
		Integer quantity = 4;

		ItemOrder itemOrder = new ItemOrder(id, orderId, productId, quantity);

		assertNotNull(itemOrder);
		assertEquals(id, itemOrder.id());
		assertEquals(orderId, itemOrder.orderId());
		assertEquals(productId, itemOrder.productId());
		assertEquals(quantity, itemOrder.quantity());
	}

	@Test
	void testItemOrderEquality() {
		ItemOrder itemOrder1 = new ItemOrder(1L, 2L, 3L, 4);
		ItemOrder itemOrder2 = new ItemOrder(1L, 2L, 3L, 4);

		assertEquals(itemOrder1, itemOrder2);
	}

	@Test
	void testItemOrderInequality() {
		ItemOrder itemOrder1 = new ItemOrder(1L, 2L, 3L, 4);
		ItemOrder itemOrder2 = new ItemOrder(1L, 2L, 3L, 5);

		assertNotEquals(itemOrder1, itemOrder2);
	}
}
