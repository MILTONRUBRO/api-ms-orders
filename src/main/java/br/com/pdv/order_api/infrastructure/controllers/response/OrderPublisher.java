package br.com.pdv.order_api.infrastructure.controllers.response;

import java.util.List;

public record OrderPublisher(String orderId,
                             List<ItemOrderPublisher> itemsOrder) {
}
