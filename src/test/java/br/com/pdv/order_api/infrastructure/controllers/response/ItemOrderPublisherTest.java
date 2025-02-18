package br.com.pdv.order_api.infrastructure.controllers.response;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ItemOrderPublisherTest {

    @Test
    public void testItemOrderPublisherConstructor() {
        ItemOrderPublisher itemOrderPublisher = new ItemOrderPublisher(1L, "Product A", "Type 1", "No observations");

        assertEquals(1L, itemOrderPublisher.productId());
        assertEquals("Product A", itemOrderPublisher.description());
        assertEquals("Type 1", itemOrderPublisher.type());
        assertEquals("No observations", itemOrderPublisher.observation());
    }

    @Test
    public void testToString() {
        ItemOrderPublisher itemOrderPublisher = new ItemOrderPublisher(2L, "Product B", "Type 2", "Fragile");

        String expectedToString = "ItemOrderPublisher[productId=2, description=Product B, type=Type 2, observation=Fragile]";
        assertEquals(expectedToString, itemOrderPublisher.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        ItemOrderPublisher item1 = new ItemOrderPublisher(3L, "Product C", "Type 3", "Urgent");
        ItemOrderPublisher item2 = new ItemOrderPublisher(3L, "Product C", "Type 3", "Urgent");

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());

        ItemOrderPublisher item3 = new ItemOrderPublisher(4L, "Product D", "Type 4", "Regular");

        assertNotEquals(item1, item3);
    }
}
