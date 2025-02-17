package br.com.pdv.order_api.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderRequest(
        @Schema(description = "Document number associated with the order",
                example = "123",
                required = false)
        String documentNumber
) {
}
