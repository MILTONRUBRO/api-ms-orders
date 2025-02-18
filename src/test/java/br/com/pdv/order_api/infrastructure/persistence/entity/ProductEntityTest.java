package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class ProductEntityTest {

    @Test
    public void testProductEntityConstructor() {
        // Criar instância simulada de CategoryEntity
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Electronics");

        // Criar o objeto ProductEntity
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("Latest model smartphone");
        product.setPrice(new BigDecimal("799.99"));
        product.setCategory(category);

        // Verificar se os valores foram atribuídos corretamente
        assertEquals(1L, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals("Latest model smartphone", product.getDescription());
        assertEquals(new BigDecimal("799.99"), product.getPrice());
        assertEquals(1L, product.getCategory().getId());
        assertEquals("Electronics", product.getCategory().getName());
    }

    @Test
    public void testToString() {
        // Criar instância simulada de CategoryEntity
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setName("Electronics");

        // Criar o objeto ProductEntity
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("Latest model smartphone");
        product.setPrice(new BigDecimal("799.99"));
        product.setCategory(category);

        // Verificar o comportamento do método toString
        String expectedToString = "ProductEntity(id=1, name=Smartphone, description=Latest model smartphone, price=799.99, category=CategoryEntity{id=1, name=Electronics})";
        assertEquals(expectedToString, product.toString());
    }

    @Test
    public void testSettersAndGetters() {
        // Criar instância simulada de CategoryEntity
        CategoryEntity category = new CategoryEntity();
        category.setId(2L);
        category.setName("Home Appliances");

        // Criar o objeto ProductEntity
        ProductEntity product = new ProductEntity();

        // Definir valores usando setters
        product.setId(2L);
        product.setName("Washing Machine");
        product.setDescription("High-efficiency washing machine");
        product.setPrice(new BigDecimal("499.99"));
        product.setCategory(category);

        // Verificar se os valores foram corretamente definidos
        assertEquals(2L, product.getId());
        assertEquals("Washing Machine", product.getName());
        assertEquals("High-efficiency washing machine", product.getDescription());
        assertEquals(new BigDecimal("499.99"), product.getPrice());
        assertEquals(2L, product.getCategory().getId());
        assertEquals("Home Appliances", product.getCategory().getName());
    }
}
