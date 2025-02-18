package br.com.pdv.order_api.infrastructure.gateways.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Optional;
import br.com.pdv.order_api.application.exception.NotFoundException;
import br.com.pdv.order_api.application.gateways.CustomerGateway;
import br.com.pdv.order_api.infrastructure.gateways.mapper.OrderEntityMapper;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatusTest;
import br.com.pdv.order_api.infrastructure.persistence.entity.PaymentStatus;
import br.com.pdv.order_api.infrastructure.persistence.repository.ItemOrderRepository;
import br.com.pdv.order_api.infrastructure.persistence.repository.OrderRepository;
import br.com.pdv.order_api.infrastructure.publisher.OrderPublisher;
import br.com.pdv.order_api.infrastructure.services.ItemOrderService;
import br.com.pdv.order_api.infrastructure.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderRepositoryGatewayTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private OrderEntityMapper orderEntityMapper;

	@Mock
	private CustomerGateway customerGateway;

	@Mock
	private ItemOrderService itemOrderService;

	@Mock
	private ItemOrderRepository itemOrderRepository;

	@Mock
	private OrderPublisher orderPublisher;

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private OrderRepositoryGateway orderRepositoryGateway;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	void testUpdateOrderStatus() {
		Long idOrder = 1L;
		OrderEntity orderEntity = new OrderEntity();
		when(orderRepository.findById(idOrder)).thenReturn(Optional.of(orderEntity));

		orderRepositoryGateway.updateOrderStatus(idOrder, "Pronto");

		verify(orderRepository).save(orderEntity);
	}

	@Test
	void testUpdateOrderStatusNotFound() {
		Long idOrder = 1L;
		when(orderRepository.findById(idOrder)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> orderRepositoryGateway.updateOrderStatus(idOrder, "COMPLETED"));
	}

	@Test
	void testGetOrderPaymentStatus() {
		Long idOrder = 1L;
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setPaymentStatus(PaymentStatus.APPROVED);
		when(orderRepository.findById(idOrder)).thenReturn(Optional.of(orderEntity));

		String status = orderRepositoryGateway.getOrderPaymentSatus(idOrder);

		assertEquals(PaymentStatus.APPROVED.getStatus(), status);
	}

	@Test
	void testGetOrderPaymentStatusNotFound() {
		Long idOrder = 1L;
		when(orderRepository.findById(idOrder)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> orderRepositoryGateway.getOrderPaymentSatus(idOrder));
	}
}
