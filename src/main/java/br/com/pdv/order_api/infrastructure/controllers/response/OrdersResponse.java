package br.com.pdv.order_api.infrastructure.controllers.response;

import java.util.List;


public record OrdersResponse(Long id, String status, String cliente, String data, String hora, List<ItemsOrdersResponse> produtosPedido) {
}
