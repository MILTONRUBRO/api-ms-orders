package br.com.pdv.order_api.infrastructure.persistence.entity;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_order")
public class ItemOrderEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity produto;

    private int quantity;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Override
    public String toString() {
        return "ItemOrder{" +
                "id=" + id +
                ", order=" + order.getId() +
                ", produto=" + produto.getId() +
                ", quantity=" + quantity +
                ", totalValue=" + totalValue +
                '}';
    }

}
