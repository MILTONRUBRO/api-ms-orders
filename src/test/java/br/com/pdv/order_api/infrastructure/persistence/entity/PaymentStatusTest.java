package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PaymentStatusTest {

    @Test
    public void testFromString() {
        // Testar a conversão de uma string para PaymentStatus
        assertEquals(PaymentStatus.APPROVED, PaymentStatus.fromString("aprovado"));
        assertEquals(PaymentStatus.REFUSED, PaymentStatus.fromString("recusado"));
    }

    @Test
    public void testFromStringWithInvalidStatus() {
        // Testar o lançamento de exceção com um status desconhecido
        assertThrows(IllegalArgumentException.class, () -> PaymentStatus.fromString("desconhecido"));
    }

    @Test
    public void testGetStatus() {
        // Testar se o método getStatus retorna o valor correto para os status
        assertEquals("aprovado", PaymentStatus.APPROVED.getStatus());
        assertEquals("recusado", PaymentStatus.REFUSED.getStatus());
    }
}
