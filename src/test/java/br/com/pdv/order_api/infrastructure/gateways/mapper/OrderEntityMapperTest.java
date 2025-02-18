package br.com.pdv.order_api.infrastructure.gateways.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import br.com.pdv.order_api.infrastructure.persistence.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.pdv.order_api.domain.entity.Order;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderEntityMapperTest {

	private OrderEntityMapper orderEntityMapper;

	@BeforeEach
	public void setUp() {
		orderEntityMapper = new OrderEntityMapper();
	}





	@Test
	public void testToResponse() {
		// Dados de entrada
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		orderEntity.setData(LocalDateTime.now());
		orderEntity.setStatus(OrderStatus.FINALIZED);
		orderEntity.setTotalValue(100.00);
		orderEntity.setClientDocument("12345678900");

		ItemOrderEntity itemOrderEntity = new ItemOrderEntity();
		itemOrderEntity.setProduto(new ProductEntity());
		itemOrderEntity.setQuantity(2);
		orderEntity.setItens(Arrays.asList(itemOrderEntity));

		// Chamada do método
		OrdersResponse ordersResponse = orderEntityMapper.toResponse(orderEntity);

		// Verificações
		assertNotNull(ordersResponse);
		assertEquals(orderEntity.getId(), ordersResponse.id());
		assertEquals(orderEntity.getStatus().getStatus(), ordersResponse.status());
	}

	@Test
	public void testToResponseList() {
		// Dados de entrada
		OrderEntity orderEntity1 = new OrderEntity();
		orderEntity1.setId(1L);
		orderEntity1.setData(LocalDateTime.now());
		orderEntity1.setStatus(OrderStatus.FINALIZED);
		orderEntity1.setTotalValue(100.00);
		orderEntity1.setClientDocument("12345678900");

		ItemOrderEntity itemOrderEntity1 = new ItemOrderEntity();
		itemOrderEntity1.setProduto(new ProductEntity());
		itemOrderEntity1.setQuantity(2);
		orderEntity1.setItens(Arrays.asList(itemOrderEntity1));

		OrderEntity orderEntity2 = new OrderEntity();
		orderEntity2.setId(2L);
		orderEntity2.setData(LocalDateTime.now());
		orderEntity2.setStatus(OrderStatus.COMPLETED);
		orderEntity2.setTotalValue(200.00);
		orderEntity2.setClientDocument("98765432100");

		ItemOrderEntity itemOrderEntity2 = new ItemOrderEntity();
		itemOrderEntity2.setProduto(new ProductEntity());
		itemOrderEntity2.setQuantity(1);
		orderEntity2.setItens(Arrays.asList(itemOrderEntity2));

		List<OrderEntity> orderEntities = Arrays.asList(orderEntity1, orderEntity2);

		// Chamada do método
		List<OrdersResponse> responseList = orderEntityMapper.toResponseList(orderEntities);

		// Verificações
		assertNotNull(responseList);
		assertEquals(2, responseList.size());
	}
}