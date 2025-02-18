package br.com.pdv.order_api.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderStatusTest {

    @Test
    public void testFromString() {
        // Testar a conversão de uma string para OrderStatus
        assertEquals(OrderStatus.RECEIVED, OrderStatus.fromString("Recebido"));
        assertEquals(OrderStatus.PROCESSING, OrderStatus.fromString("Em Preparação"));
        assertEquals(OrderStatus.COMPLETED, OrderStatus.fromString("Pronto"));
        assertEquals(OrderStatus.FINALIZED, OrderStatus.fromString("Finalizado"));
    }

    @Test
    public void testFromStringWithInvalidStatus() {
        // Testar o lançamento de exceção com um status desconhecido
        assertThrows(IllegalArgumentException.class, () -> OrderStatus.fromString("Desconhecido"));
    }

    @Test
    public void testGetStatus() {
        // Testar se o método getStatus retorna o valor correto para os status
        assertEquals("Recebido", OrderStatus.RECEIVED.getStatus());
        assertEquals("Em Preparação", OrderStatus.PROCESSING.getStatus());
        assertEquals("Pronto", OrderStatus.COMPLETED.getStatus());
        assertEquals("Finalizado", OrderStatus.FINALIZED.getStatus());
    }
}
