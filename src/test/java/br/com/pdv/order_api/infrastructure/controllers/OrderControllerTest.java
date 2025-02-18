package br.com.pdv.order_api.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pdv.order_api.application.usecase.OrderCreateInteractor;
import br.com.pdv.order_api.application.usecase.UpdateOrderStatusInteractor;
import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.controllers.request.UpdateOrderStatusRequest;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatusTest;

class OrderControllerTest {

		@InjectMocks
		private OrderController orderController;

		@Mock
		private OrderCreateInteractor createOrderUseCase;

		@Mock
		private UpdateOrderStatusInteractor updateOrderStatusInteractor;

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
			
			when(createOrderUseCase.createOrder(orderRequest)).thenReturn(order);

			ResponseEntity<Void> response = orderController.createOrder(orderRequest);

			assertEquals(HttpStatus.CREATED, response.getStatusCode());
			assertNotNull(response.getHeaders().getLocation());
		}

		@Test
		void testUpdateOrderStatus() {
			Long idOrder = 1L;
			UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest("finalizado");

			doNothing().when(updateOrderStatusInteractor).updateOrderStatus(idOrder, "COMPLETED");

			ResponseEntity<Void> response = orderController.updateOrderStatus(idOrder, updateOrderStatusRequest);

			assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		}

}
