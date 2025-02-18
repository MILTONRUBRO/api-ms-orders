package br.com.pdv.order_api.application.usecase;

import static org.junit.jupiter.api.Assertions.*;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pdv.order_api.application.gateways.OrderGateway;
import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatusTest;

class OrderCreateInteractorTest {

	@Mock
	private OrderGateway orderGateway;

	@InjectMocks
	private OrderCreateInteractor orderCreateInteractor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateOrder() {
		OrderRequest orderRequest = new OrderRequest("123");

		OrderStatus status = OrderStatus.RECEIVED;
        List<ItemOrder> itemsOrder = List.of();

        Order order = new Order(
            1L,
            LocalDateTime.now(),
            status,
            14.97,
            "12345678900",
            itemsOrder
        );
		
		when(orderGateway.createOrder(orderRequest)).thenReturn(order);

		Order result = orderCreateInteractor.createOrder(orderRequest);

		assertNotNull(result);
		assertEquals(order, result);
		verify(orderGateway, times(1)).createOrder(orderRequest);
	}

}
