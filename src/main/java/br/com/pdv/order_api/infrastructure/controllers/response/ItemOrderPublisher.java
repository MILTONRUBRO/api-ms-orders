package br.com.pdv.order_api.infrastructure.controllers.response;

public record ItemOrderPublisher(Long productId, String description, String type, String observation) {
}