package br.com.pdv.order_api.domain.entity;

public record ItemOrder(Long id, Long orderId, Long productId, Integer quantity) {
}