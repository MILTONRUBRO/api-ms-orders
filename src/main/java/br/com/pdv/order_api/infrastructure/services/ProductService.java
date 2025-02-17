package br.com.pdv.order_api.infrastructure.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.pdv.order_api.infrastructure.persistence.entity.CategoryEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.ProductEntity;

public class ProductService {
	
    public static List<ProductEntity> criarProdutosMockados() {
        List<ProductEntity> produtos = new ArrayList<>();

        CategoryEntity burgers = new CategoryEntity(1L, "Hambúrgueres");
        CategoryEntity drinks = new CategoryEntity(2L, "Bebidas");
        CategoryEntity sides = new CategoryEntity(3L, "Acompanhamentos");

        produtos.add(new ProductEntity(1L, "Cheeseburger", "Delicioso cheeseburger com alface, tomate e queijo", BigDecimal.valueOf(5.99), burgers));
        produtos.add(new ProductEntity(2L, "Sanduíche de Frango", "Sanduíche de frango crocante com picles e maionese", BigDecimal.valueOf(6.49), burgers));
        produtos.add(new ProductEntity(3L, "Hambúrguer Vegano", "Saboroso hambúrguer vegano com abacate e espinafre", BigDecimal.valueOf(6.99), burgers));

        produtos.add(new ProductEntity(4L, "Cola", "Bebida de cola refrescante", BigDecimal.valueOf(1.99), drinks));
        produtos.add(new ProductEntity(5L, "Limonada", "Limonada recém espremida", BigDecimal.valueOf(2.49), drinks));
        produtos.add(new ProductEntity(6L, "Milkshake", "Milkshake cremoso de baunilha", BigDecimal.valueOf(3.49), drinks));

        produtos.add(new ProductEntity(7L, "Batata Frita", "Batata frita dourada e crocante", BigDecimal.valueOf(2.99), sides));
        produtos.add(new ProductEntity(8L, "Anéis de Cebola", "Anéis de cebola crocantes", BigDecimal.valueOf(3.49), sides));
        produtos.add(new ProductEntity(9L, "Salada", "Salada fresca do jardim com molho", BigDecimal.valueOf(4.99), sides));

        return produtos;
    }
    
    public static ProductEntity criarProdutoMockado() {
        CategoryEntity hamburgueres = new CategoryEntity(1L, "Hambúrgueres");
        return new ProductEntity(null, "Cheeseburger", "Delicioso cheeseburger com alface, tomate e queijo", BigDecimal.valueOf(5.99), hamburgueres);
    }

}
