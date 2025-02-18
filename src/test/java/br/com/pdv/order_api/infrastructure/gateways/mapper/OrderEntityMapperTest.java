package br.com.pdv.order_api.infrastructure.gateways.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatusTest;

class OrderEntityMapperTest {

		private OrderEntityMapper orderEntityMapper;

		@BeforeEach
		void setUp() {
			orderEntityMapper = new OrderEntityMapper();
		}

		@Test
		void testToDomainObj() {
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setId(1L);
			orderEntity.setData(LocalDateTime.now());
			orderEntity.setStatus(OrderStatus.FINALIZED);
			orderEntity.setTotalValue(100.0);
			orderEntity.setClientDocument("123456789");

			Order order = orderEntityMapper.toDomainObj(orderEntity);

			assertNotNull(order);
			assertEquals(orderEntity.getId(), order.id());
			assertEquals(orderEntity.getData(), order.data());
			assertEquals(orderEntity.getStatus(), order.status());
			assertEquals(orderEntity.getTotalValue(), order.totalValue());
			assertEquals(orderEntity.getClientDocument(), order.customerDocument());
		}


	}
