package br.com.pdv.order_api.infrastructure.configuration;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Fila de pedidos
    @Bean
    public Queue queueOrders() {
        return QueueBuilder.durable("queue.orders")
                .withArgument("x-dead-letter-exchange", "dlx-exchange")
                .withArgument("x-dead-letter-routing-key", "dlx-queue.orders")
                .build();
    }

    // Fila DLX para mensagens expiradas ou com erro
    @Bean
    public Queue queueDLX() {
        return new Queue("dlx-queue.orders", true);
    }

    // Exchange de mensagens
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("queue.orders");  // Exchange com o mesmo nome da fila
    }

    // Binding entre fila e exchange
    @Bean
    public Binding binding(Queue queueOrders, DirectExchange exchange) {
        return BindingBuilder.bind(queueOrders).to(exchange).with("queue.orders");  // Bind a fila com a exchange
    }

    // Exchange DLX
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx-exchange");
    }

    // Binding entre fila DLX e a exchange DLX
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(queueDLX()).to(dlxExchange()).with("dlx-queue.orders");
    }
}
