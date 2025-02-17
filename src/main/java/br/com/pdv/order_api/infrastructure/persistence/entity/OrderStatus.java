package br.com.pdv.order_api.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum OrderStatus {
	
    RECEIVED("Recebido"),
    PROCESSING("Em Preparação"),
    COMPLETED("Pronto"),
    FINALIZED("Finalizado");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.status.equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Status desconhecido: " + status);
    }

}
