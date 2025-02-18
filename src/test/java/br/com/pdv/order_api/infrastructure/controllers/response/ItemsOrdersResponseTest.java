package br.com.pdv.order_api.infrastructure.controllers.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemsOrdersResponseTest {

	@Test
	void testItemsOrdersResponseCreation() {
		ItemsOrdersResponse response = new ItemsOrdersResponse("Item1", "10");
		assertNotNull(response);
		assertEquals("Item1", response.descricao());
		assertEquals("10", response.quantidade());
	}

	@Test
	void testItemsOrdersResponseDescricao() {
		ItemsOrdersResponse response = new ItemsOrdersResponse("Item2", "5");
		assertEquals("Item2", response.descricao());
	}

	@Test
	void testItemsOrdersResponseQuantidade() {
		ItemsOrdersResponse response = new ItemsOrdersResponse("Item3", "15");
		assertEquals("15", response.quantidade());
	}

	@Test
	void testItemsOrdersResponseEquality() {
		ItemsOrdersResponse response1 = new ItemsOrdersResponse("Item4", "20");
		ItemsOrdersResponse response2 = new ItemsOrdersResponse("Item4", "20");
		assertEquals(response1, response2);
	}

	@Test
	void testItemsOrdersResponseInequality() {
		ItemsOrdersResponse response1 = new ItemsOrdersResponse("Item5", "25");
		ItemsOrdersResponse response2 = new ItemsOrdersResponse("Item6", "30");
		assertNotEquals(response1, response2);
	}
}
