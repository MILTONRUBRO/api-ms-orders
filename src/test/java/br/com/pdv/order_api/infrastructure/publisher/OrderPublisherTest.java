package br.com.pdv.order_api.infrastructure.publisher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.gateways.mapper.OrderMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderPublisherTest {

	@Mock
	private RabbitTemplate rabbitTemplate;

	@Mock
	private ObjectMapper objectMapper;

	@InjectMocks
	private OrderPublisher orderPublisher;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testPublishOrderSuccess() throws JsonProcessingException {
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);

		
		OrderMapper mapper = new OrderMapper();
		br.com.pdv.order_api.infrastructure.controllers.response.OrderPublisher orderPublisherResponse = mapper
				.mapToOrderPublishers(Collections.singletonList(ordersResponse)).get(0);
		String message = "testMessage";

		when(objectMapper.writeValueAsString(orderPublisherResponse)).thenReturn(message);

		orderPublisher.publishOrder(Collections.singletonList(ordersResponse));

		verify(rabbitTemplate, times(1)).convertAndSend("queue.orders", "queue.orders", message);
	}

	@Test
	void testPublishOrderJsonProcessingException() throws JsonProcessingException {
		
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);

		when(objectMapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("Error") {
		});

		orderPublisher.publishOrder(Collections.singletonList(ordersResponse));

		verify(rabbitTemplate, never()).convertAndSend(anyString(), anyString(), anyString());
	}

	@Test
	void testPublishOrderAmqpException() throws JsonProcessingException {
		
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);
		OrderMapper mapper = new OrderMapper();
		br.com.pdv.order_api.infrastructure.controllers.response.OrderPublisher orderPublisherResponse = mapper
				.mapToOrderPublishers(Collections.singletonList(ordersResponse)).get(0);
		String message = "testMessage";

		when(objectMapper.writeValueAsString(orderPublisherResponse)).thenReturn(message);
		doThrow(new AmqpException("Error")).when(rabbitTemplate).convertAndSend(anyString(), anyString(), anyString());

		orderPublisher.publishOrder(Collections.singletonList(ordersResponse));

		verify(rabbitTemplate, times(1)).convertAndSend("queue.orders", "queue.orders", message);
	}
}
