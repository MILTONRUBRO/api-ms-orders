package br.com.pdv.order_api.infrastructure.gateways.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Collections;
import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.ProductEntity;

class ItemOrderEntityMapperTest {

	private final ItemOrderEntityMapper mapper = new ItemOrderEntityMapper();

	@Test
	void testToDomainObj() {
		ItemOrderEntity itemOrderEntity = new ItemOrderEntity();
		itemOrderEntity.setId(1L);
		itemOrderEntity.setQuantity(10);

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		itemOrderEntity.setOrder(orderEntity);

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(1L);
		itemOrderEntity.setProduto(productEntity);

		ItemOrder itemOrder = mapper.toDomainObj(itemOrderEntity);

		assertNotNull(itemOrder);
		assertEquals(1L, itemOrder.id());
		assertEquals(1L, itemOrder.orderId());
		assertEquals(1L, itemOrder.productId());
		assertEquals(10, itemOrder.quantity());
	}

	@Test
	void testToDomainObjList() {
		ItemOrderEntity itemOrderEntity = new ItemOrderEntity();
		itemOrderEntity.setId(1L);
		itemOrderEntity.setQuantity(10);

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(1L);
		itemOrderEntity.setOrder(orderEntity);

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(1L);
		itemOrderEntity.setProduto(productEntity);

		List<ItemOrderEntity> itemOrderEntities = Collections.singletonList(itemOrderEntity);

		List<ItemOrder> itemOrders = mapper.toDomainObj(itemOrderEntities);

		assertNotNull(itemOrders);
		assertEquals(1, itemOrders.size());
		ItemOrder itemOrder = itemOrders.get(0);
		assertEquals(1L, itemOrder.id());
		assertEquals(1L, itemOrder.orderId());
		assertEquals(1L, itemOrder.productId());
		assertEquals(10, itemOrder.quantity());
	}

	@Test
	void testToEntity() {
		ItemOrder itemOrder = new ItemOrder(1L, 1L, 1L, 10);

		ItemOrderEntity itemOrderEntity = mapper.toEntity(itemOrder);

		assertNotNull(itemOrderEntity);
		assertEquals(1L, itemOrderEntity.getId());
		assertEquals(10, itemOrderEntity.getQuantity());
		assertNotNull(itemOrderEntity.getOrder());
		assertEquals(1L, itemOrderEntity.getOrder().getId());
		assertNotNull(itemOrderEntity.getProduto());
		assertEquals(1L, itemOrderEntity.getProduto().getId());
	}
}
