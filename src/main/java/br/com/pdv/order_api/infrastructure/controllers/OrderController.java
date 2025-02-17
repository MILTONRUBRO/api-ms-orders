package br.com.pdv.order_api.infrastructure.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pdv.order_api.application.usecase.OrderCreateInteractor;
import br.com.pdv.order_api.application.usecase.UpdateOrderStatusInteractor;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.controllers.request.UpdateOrderStatusRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/orders")
@Log4j2
public class OrderController {
	
	@Autowired
	private  OrderCreateInteractor createOrderUseCase;
	
	
	@Autowired
	private  UpdateOrderStatusInteractor updateOrderStatusInteractor;


	@Operation(summary = "Create a new order",
			description = "Creates a new order based on the provided document number.",
			responses = {
					@ApiResponse(responseCode = "201", description = "Order created successfully"),
					@ApiResponse(responseCode = "400", description = "Invalid input")
			})
	@PostMapping
	public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request) {
		log.info("POST Order Request: {}", request);
		//Order order = orderDTOMapper.toOrder(request);
		Order orderCreated = createOrderUseCase.createOrder(request);
		URI location = URI.create("/orders/" + orderCreated.id());
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Update the status of an order",
			description = "Updates the status of the specified order based on the provided status.",
			responses = {
					@ApiResponse(responseCode = "204", description = "Order status updated successfully"),
					@ApiResponse(responseCode = "404", description = "Order not found"),
					@ApiResponse(responseCode = "400", description = "Invalid input")
			})
	@PatchMapping("/{idOrder}")
	public ResponseEntity<Void> updateOrderStatus(@PathVariable Long idOrder, @RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest) {
		log.info("PATCH update status for Order ID: {}", idOrder);
		updateOrderStatusInteractor.updateOrderStatus(idOrder, updateOrderStatusRequest.status());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
