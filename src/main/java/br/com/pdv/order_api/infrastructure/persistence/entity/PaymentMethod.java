package br.com.pdv.order_api.infrastructure.persistence.entity;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    PIX("PIX"),
    QR_CODE("QR Code"),
    CASH("Dinheiro"),
    CREDIT_CARD("Cartão de Crédito");


    private final String paymentType;

    PaymentMethod(String displayName) {
        this.paymentType = displayName;
    }

}
