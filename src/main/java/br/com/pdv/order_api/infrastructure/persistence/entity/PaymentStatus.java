package br.com.pdv.order_api.infrastructure.persistence.entity;

public enum PaymentStatus {
	
    APPROVED("aprovado"),
    REFUSED("recusado");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static PaymentStatus fromString(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.status.equalsIgnoreCase(status)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Status desconhecido: " + status);
    }

}
