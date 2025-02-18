package br.com.pdv.order_api.application.usecase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.pdv.order_api.application.gateways.OrderGateway;
import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;

class UpdateOrderStatusInteractorTest {


		@Mock
		private OrderGateway orderGateway;

		@InjectMocks
		private UpdateOrderStatusInteractor updateOrderStatusInteractor;

		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
		}

		@Test
		void testUpdateOrderStatus() {
			Long idOrder = 1L;			
			String status = "Completed";
			String cliente = "Jane Smith";
			String data = "2023-10-02";
			String hora = "11:00";
			List<ItemsOrdersResponse> produtosPedido = List.of();

			OrdersResponse ordersResponse = new OrdersResponse(idOrder, status, cliente, data, hora, produtosPedido);
			List<OrdersResponse> productionOrders = Collections.singletonList(ordersResponse);

			when(orderGateway.getAllOrdersOrdenedInteractor(Optional.of(idOrder))).thenReturn(productionOrders);

			updateOrderStatusInteractor.updateOrderStatus(idOrder, status);

			verify(orderGateway).updateOrderStatus(idOrder, status);
			verify(orderGateway).getAllOrdersOrdenedInteractor(Optional.of(idOrder));
			verify(orderGateway).publishProductionOrder(productionOrders);
		}

		@Test
		void testUpdateOrderStatusWithEmptyOrders() {
			Long idOrder = 1L;
			String status = "COMPLETED";
			List<OrdersResponse> productionOrders = Collections.emptyList();

			when(orderGateway.getAllOrdersOrdenedInteractor(Optional.of(idOrder))).thenReturn(productionOrders);

			updateOrderStatusInteractor.updateOrderStatus(idOrder, status);

			verify(orderGateway).updateOrderStatus(idOrder, status);
			verify(orderGateway).getAllOrdersOrdenedInteractor(Optional.of(idOrder));
			verify(orderGateway).publishProductionOrder(productionOrders);
		}

	}


