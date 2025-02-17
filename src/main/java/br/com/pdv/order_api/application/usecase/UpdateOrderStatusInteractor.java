package br.com.pdv.order_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.application.gateways.OrderGateway;

@Service
public class UpdateOrderStatusInteractor {

	@Autowired
	private  OrderGateway orderGateway;

	public void updateOrderStatus(Long idOrder, String status) {
		orderGateway.updateOrderStatus(idOrder, status);
	}

}
