package br.com.pdv.order_api.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.application.gateways.OrderGateway;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;

@Service
public class OrderCreateInteractor {
	
	@Autowired
    private  OrderGateway orderGateway;

    public Order createOrder(OrderRequest request) {
        return orderGateway.createOrder(request);
    }

}
