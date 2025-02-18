package br.com.pdv.order_api.application.usecase;

import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.application.gateways.OrderGateway;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateOrderStatusInteractor {

	@Autowired
	private  OrderGateway orderGateway;

	public void updateOrderStatus(Long idOrder, String status) {
		orderGateway.updateOrderStatus(idOrder, status);
		List<OrdersResponse>productionOrders = orderGateway.getAllOrdersOrdenedInteractor(Optional.of(idOrder));
		orderGateway.publishProductionOrder(productionOrders);
	}

}
