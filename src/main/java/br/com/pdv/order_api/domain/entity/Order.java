package br.com.pdv.order_api.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;

public record Order(Long id,
        LocalDateTime data,
        OrderStatus status,
        Double totalValue,
        String customerDocument,
       // Set<Payment> payments,
        List<ItemOrder> itemsOrder) {
	
}