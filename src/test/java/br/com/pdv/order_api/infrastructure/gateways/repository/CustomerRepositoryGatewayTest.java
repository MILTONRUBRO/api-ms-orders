package br.com.pdv.order_api.infrastructure.gateways.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.gateways.mapper.OrderEntityMapper;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import br.com.pdv.order_api.infrastructure.persistence.repository.OrderRepository;
import br.com.pdv.order_api.infrastructure.publisher.OrderPublisher;
import br.com.pdv.order_api.infrastructure.services.ItemOrderService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;
import br.com.pdv.order_api.infrastructure.services.CustomerService;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class CustomerRepositoryGatewayTest {

	@Mock
	private CustomerService customerService;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private OrderEntityMapper orderEntityMapper;

	@InjectMocks
	private OrderRepositoryGateway orderRepositoryGateway;

	@Mock
	private ItemOrderService itemOrderService;
	@InjectMocks
	private CustomerRepositoryGateway customerRepositoryGateway;

	@Mock
	private OrderPublisher orderPublisher;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		//orderRepositoryGateway = new OrderRepositoryGateway();
	}

	@Test
	void testFindByDocument() {
		String document = "123456789";
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		when(customerService.buscarClientePorCPF(document)).thenReturn(customerResponseDTO);

		CustomerResponseDTO result = customerRepositoryGateway.findByDocument(document);

		assertNotNull(result);
		assertEquals(customerResponseDTO, result);
		verify(customerService, times(1)).buscarClientePorCPF(document);
	}

	@Test
	void testFindByDocumentForOrder() {
		String document = "123456789";
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		when(customerService.buscarClientePorCPF(document)).thenReturn(customerResponseDTO);

		Optional<CustomerResponseDTO> result = customerRepositoryGateway.findByDocumentForOrder(document);

		assertTrue(result.isPresent());
		assertEquals(customerResponseDTO, result.get());
		verify(customerService, times(1)).buscarClientePorCPF(document);
	}

	@Test
	void testGetAllOrdersOrdenedInteractorWithoutId() {
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);
		OrderEntity order1 = new OrderEntity();
		order1.setStatus(OrderStatus.RECEIVED);
		order1.setData(LocalDateTime.now().minusDays(1));

		OrderEntity order2 = new OrderEntity();
		order2.setStatus(OrderStatus.PROCESSING);
		order2.setData(LocalDateTime.now());

		List<OrderEntity> orderEntities = Arrays.asList(order1, order2);

		when(orderRepository.findById(any())).thenReturn(Optional.of(order2));

		when(orderRepository.findByStatusNot(OrderStatus.FINALIZED)).thenReturn(orderEntities);
		when(orderEntityMapper.toResponse(any(OrderEntity.class))).thenReturn(ordersResponse);

		List<OrdersResponse> result = orderRepositoryGateway.getAllOrdersOrdenedInteractor(Optional.of(1L));

		assertEquals(1, result.size());
		assertTrue(result.get(0) instanceof OrdersResponse);
	}

	@Test
	void testGetAllOrdersOrdenedInteractorWithoutIdNullOfLong() {
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);
		OrderEntity order1 = new OrderEntity();
		order1.setStatus(OrderStatus.RECEIVED);
		order1.setData(LocalDateTime.now().minusDays(1));

		OrderEntity order2 = new OrderEntity();
		order2.setStatus(OrderStatus.PROCESSING);
		order2.setData(LocalDateTime.now());

		List<OrderEntity> orderEntities = Arrays.asList(order1, order2);

		when(orderRepository.findById(any())).thenReturn(Optional.of(order2));

		when(orderRepository.findByStatusNot(OrderStatus.FINALIZED)).thenReturn(orderEntities);
		when(orderEntityMapper.toResponse(any(OrderEntity.class))).thenReturn(ordersResponse);

		List<OrdersResponse> result = orderRepositoryGateway.getAllOrdersOrdenedInteractor(Optional.empty());

		assertEquals(2, result.size());
		assertTrue(result.get(0) instanceof OrdersResponse);
	}

	@Test
	void testGetAllOrdersOrdenedInteractorWithId() {
		Long orderId = 1L;
		OrderEntity order = new OrderEntity();
		order.setId(orderId);
		order.setStatus(OrderStatus.RECEIVED);
		order.setData(LocalDateTime.now());

		List<OrderEntity> orderEntities = Collections.singletonList(order);
		Long id = 2L;
		String status = "Completed";
		String cliente = "Jane Smith";
		String data = "2023-10-02";
		String hora = "11:00";
		List<ItemsOrdersResponse> produtosPedido = List.of();

		OrdersResponse ordersResponse = new OrdersResponse(id, status, cliente, data, hora, produtosPedido);
		when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
		when(orderEntityMapper.toResponse(any(OrderEntity.class))).thenReturn(ordersResponse);

		List<OrdersResponse> result = orderRepositoryGateway.getAllOrdersOrdenedInteractor(Optional.of(orderId));

		assertEquals(1, result.size());
		verify(orderRepository).findById(orderId);
		verify(orderEntityMapper).toResponse(any(OrderEntity.class));
	}

	@Test
	void testPublishProductionOrder() {
		List<OrdersResponse> ordersResponses = new ArrayList<>();
		orderRepositoryGateway.publishProductionOrder(ordersResponses);

		verify(orderPublisher).publishOrder(ordersResponses);
	}
}
