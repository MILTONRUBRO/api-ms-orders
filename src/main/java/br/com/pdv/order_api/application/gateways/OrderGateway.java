package br.com.pdv.order_api.application.gateways;

import java.util.List;

import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;

public interface OrderGateway {
    Order createOrder(OrderRequest request);
    void updateOrderStatus(Long idOrder, String status);
    void updateOrderPayments(Long idOrder, String status);
    String getOrderPaymentSatus(Long idOrder);
    List<OrdersResponse> getAllOrdersOrdenedInteractor();

}
