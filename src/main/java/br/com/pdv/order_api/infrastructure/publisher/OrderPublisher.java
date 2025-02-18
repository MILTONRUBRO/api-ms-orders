package br.com.pdv.order_api.infrastructure.publisher;

import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.gateways.mapper.OrderMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class OrderPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private static final String EXCHANGE_NAME = "queue.orders";
    private static final String ROUTING_KEY = "queue.orders";

    public void publishOrder(List<OrdersResponse> ordersResponses) {
        try {
            OrderMapper mapper = new OrderMapper();
            List<br.com.pdv.order_api.infrastructure.controllers.response.OrderPublisher> orderPublishers = mapper.mapToOrderPublishers(ordersResponses);
            String message = objectMapper.writeValueAsString(orderPublishers.get(0));
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);

            logger.info("🚀 Pedido publicado com sucesso: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("💥 Erro ao serializar pedido: {}", e.getMessage(), e);
        } catch (AmqpException e) {
            logger.error("📡 Erro ao publicar pedido no RabbitMQ: {}", e.getMessage(), e);
        }
    }
}
