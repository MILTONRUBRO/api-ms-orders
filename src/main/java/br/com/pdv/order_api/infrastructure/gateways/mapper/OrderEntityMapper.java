package br.com.pdv.order_api.infrastructure.gateways.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.response.ItemsOrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;

@Service
public class OrderEntityMapper {
	
  //  private final CustomerEntityMapper customerMapper = new CustomerEntityMapper();
  //  private final PaymentEntityMapper paymentMapper = new PaymentEntityMapper();
    private final ItemOrderEntityMapper itemOrderMapper = new ItemOrderEntityMapper();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    public Order toDomainObj(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getData(),
                orderEntity.getStatus(),
                orderEntity.getTotalValue(),
                orderEntity.getClientDocument(),
                //customerMapper.entityToCustomer(orderEntity.getCustomer()),
               // paymentMapper.toDomainObjSet(orderEntity.getPayments()),
                itemOrderMapper.toDomainObj(orderEntity.getItens())
        );
    }

    public List<Order> toDomainObj(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(this::toDomainObj)
                .collect(Collectors.toList());
    }

    public OrderEntity toEntity(Order order) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.id());
        orderEntity.setData(order.data());
        if (order.status() != null) {
            orderEntity.setStatus(OrderStatus.valueOf(order.status().getStatus()));
        }
       orderEntity.setTotalValue(order.totalValue());
       //orderEntity.setCustomer(customerMapper.customerToEntity(order.customer()));

//        if (Objects.nonNull(order.payments())) {
//            Set<PaymentEntity> paymentEntities = order.payments().stream()
//                    .map(paymentMapper::toEntity)
//                    .collect(Collectors.toSet());
//            orderEntity.setPayments(paymentEntities);
//        }

        return orderEntity;
    }

    private OrderStatus mapStatus(OrderStatus entityStatus) {
        return entityStatus;
    }

    public OrdersResponse toResponse(OrderEntity orderEntity) {
        return new OrdersResponse(
                orderEntity.getId(),
                orderEntity.getStatus().getStatus(),
               // orderEntity.getCustomer().getName(),
                "",
                formatDate(orderEntity.getData()),
                formatTime(orderEntity.getData()),
                mapItems(orderEntity.getItens())
        );
    }

    public List<OrdersResponse> toResponseList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(dateFormatter);
    }

    private String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.format(timeFormatter);
    }

    private List<ItemsOrdersResponse> mapItems(List<ItemOrderEntity> items) {
        return items.stream()
                .map(item -> new ItemsOrdersResponse(
                        item.getProduto().getDescription(),
                        String.valueOf(item.getQuantity())
                ))
                .collect(Collectors.toList());
    }

}
