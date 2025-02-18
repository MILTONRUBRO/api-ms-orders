package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CategoryEntityTest {

    @Test
    public void testCategoryEntityConstructor() {
        CategoryEntity category = new CategoryEntity(1L, "Electronics");

        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    public void testCategoryEntityDefaultConstructor() {
        CategoryEntity category = new CategoryEntity();

        assertNull(category.getId());
        assertNull(category.getName());
    }

    @Test
    public void testSettersAndGetters() {
        CategoryEntity category = new CategoryEntity();

        category.setId(2L);
        category.setName("Home Appliances");

        assertEquals(2L, category.getId());
        assertEquals("Home Appliances", category.getName());
    }

    @Test
    public void testToString() {
        CategoryEntity category = new CategoryEntity(3L, "Furniture");

        String expectedToString = "CategoryEntity{id=3, name=Furniture}";
    }
}
