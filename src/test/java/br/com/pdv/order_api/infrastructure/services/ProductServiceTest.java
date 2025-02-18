package br.com.pdv.order_api.infrastructure.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import br.com.pdv.order_api.infrastructure.persistence.entity.ProductEntity;

class ProductServiceTest {

	@Test
	void testCriarProdutosMockados() {
		List<ProductEntity> produtos = ProductService.criarProdutosMockados();
		assertNotNull(produtos);
		assertEquals(9, produtos.size());

		ProductEntity firstProduct = produtos.get(0);
		assertEquals("Cheeseburger", firstProduct.getName());
		assertEquals(BigDecimal.valueOf(5.99), firstProduct.getPrice());
	}

	@Test
	void testCriarProdutoMockado() {
		ProductEntity produto = ProductService.criarProdutoMockado();
		assertNotNull(produto);
		assertEquals("Cheeseburger", produto.getName());
		assertEquals(BigDecimal.valueOf(5.99), produto.getPrice());
		assertEquals("Hambúrgueres", produto.getCategory().getName());
	}
}
