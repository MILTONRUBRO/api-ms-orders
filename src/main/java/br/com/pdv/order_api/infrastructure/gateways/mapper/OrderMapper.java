package br.com.pdv.order_api.infrastructure.gateways.mapper;

import br.com.pdv.order_api.infrastructure.controllers.response.ItemOrderPublisher;
import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrderPublisher;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private ItemOrderPublisher mapToItemOrderPublisher(ItemsOrdersResponse itemsOrdersResponse) {
        Integer quantity = Integer.parseInt(itemsOrdersResponse.quantidade());
        return new ItemOrderPublisher(
                null,
                itemsOrdersResponse.descricao(),
                "COMIDA",
                ""
        );
    }

    public OrderPublisher mapToOrderPublisher(OrdersResponse ordersResponse) {
        List<ItemOrderPublisher> itemsOrder = ordersResponse.produtosPedido().stream()
                .map(this::mapToItemOrderPublisher)
                .collect(Collectors.toList());

        return new OrderPublisher(
                ordersResponse.id().toString(),
                itemsOrder
        );
    }

    public List<OrderPublisher> mapToOrderPublishers(List<OrdersResponse> ordersResponses) {
        return ordersResponses.stream()
                .map(this::mapToOrderPublisher)
                .collect(Collectors.toList());
    }
}
