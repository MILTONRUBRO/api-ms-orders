package br.com.pdv.order_api.infrastructure.controllers.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

class OrdersResponseTest {

	@Test
	void testOrdersResponseCreation() {
		Long id = 1L;
		String status = "Pending";
		String cliente = "John Doe";
		String data = "2023-10-01";
		String hora = "10:00";
		List<ItemsOrdersResponse> produtosPedido = List.of(new ItemsOrdersResponse("Product1", "2"));

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);

		assertNotNull(ordersResponse);
		assertEquals(id, ordersResponse.id());
		assertEquals(status, ordersResponse.status());
		assertEquals(cliente, ordersResponse.cliente());
		assertEquals(data, ordersResponse.data());
		assertEquals(hora, ordersResponse.hora());
		assertEquals(produtosPedido, ordersResponse.produtosPedido());
	}

	@Test
	void testOrdersResponseWithEmptyProducts() {
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);

		assertNotNull(ordersResponse);
		assertEquals(id, ordersResponse.id());
		assertEquals(status, ordersResponse.status());
		assertEquals(cliente, ordersResponse.cliente());
		assertEquals(data, ordersResponse.data());
		assertEquals(hora, ordersResponse.hora());
		assertTrue(ordersResponse.produtosPedido().isEmpty());
	}
}
