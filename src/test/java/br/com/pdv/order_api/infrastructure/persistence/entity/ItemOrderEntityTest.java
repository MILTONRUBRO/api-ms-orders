package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class ItemOrderEntityTest {

    @Test
    public void testItemOrderEntityConstructor() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(2L);

        ItemOrderEntity itemOrder = new ItemOrderEntity();
        itemOrder.setId(3L);
        itemOrder.setOrder(order);
        itemOrder.setProduto(product);
        itemOrder.setQuantity(5);
        itemOrder.setTotalValue(new BigDecimal("100.00"));

        assertEquals(3L, itemOrder.getId());
        assertEquals(1L, itemOrder.getOrder().getId());
        assertEquals(2L, itemOrder.getProduto().getId());
        assertEquals(5, itemOrder.getQuantity());
        assertEquals(new BigDecimal("100.00"), itemOrder.getTotalValue());
    }

    @Test
    public void testToString() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(2L);

        ItemOrderEntity itemOrder = new ItemOrderEntity();
        itemOrder.setId(3L);
        itemOrder.setOrder(order);
        itemOrder.setProduto(product);
        itemOrder.setQuantity(5);
        itemOrder.setTotalValue(new BigDecimal("100.00"));

        String expectedToString = "ItemOrder{id=3, order=1, produto=2, quantity=5, totalValue=100.00}";
        assertEquals(expectedToString, itemOrder.toString());
    }

    @Test
    public void testSettersAndGetters() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(2L);

        ItemOrderEntity itemOrder = new ItemOrderEntity();

        itemOrder.setId(3L);
        itemOrder.setOrder(order);
        itemOrder.setProduto(product);
        itemOrder.setQuantity(10);
        itemOrder.setTotalValue(new BigDecimal("200.00"));

        assertEquals(3L, itemOrder.getId());
        assertEquals(1L, itemOrder.getOrder().getId());
        assertEquals(2L, itemOrder.getProduto().getId());
        assertEquals(10, itemOrder.getQuantity());
        assertEquals(new BigDecimal("200.00"), itemOrder.getTotalValue());
    }
}
