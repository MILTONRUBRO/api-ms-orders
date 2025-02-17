package br.com.pdv.order_api.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateOrderStatusRequest(@Schema(description = "Status of the order", example = "Finalizado") String status) {

}
