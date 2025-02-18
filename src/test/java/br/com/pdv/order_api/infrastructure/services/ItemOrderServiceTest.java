package br.com.pdv.order_api.infrastructure.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.repository.ProductRepository;

class ItemOrderServiceTest {

		@InjectMocks
		private ItemOrderService itemOrderService;

		@Mock
		private ProductRepository productRepository;

		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
		}

		@Test
		void testCriarItensOrderMockados() {
			OrderEntity orderEntity = new OrderEntity();
			List<ItemOrderEntity> itensOrder = itemOrderService.criarItensOrderMockados(orderEntity);

			assertNotNull(itensOrder);
			assertEquals(1, itensOrder.size());

			ItemOrderEntity item = itensOrder.get(0);
			assertEquals(1, item.getQuantity());
			assertEquals(orderEntity, item.getOrder());
			assertNotNull(item.getProduto());
			assertEquals("Cheeseburger", item.getProduto().getName());
		}
	}

