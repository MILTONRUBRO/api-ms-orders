package br.com.pdv.order_api.infrastructure.gateways.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.ProductEntity;

public class ItemOrderEntityMapper {
	
    public ItemOrder toDomainObj(ItemOrderEntity itemOrderEntity) {
        return new ItemOrder(
                itemOrderEntity.getId(),
                itemOrderEntity.getOrder().getId(),
                itemOrderEntity.getProduto().getId(),
               // itemOrderEntity.getOrder().getId(),
              //  itemOrderEntity.getProduto().getId(),
                itemOrderEntity.getQuantity()
        );
    }

    public List<ItemOrder> toDomainObj(List<ItemOrderEntity> itemOrderEntities) {
        if (Objects.nonNull(itemOrderEntities)) {
            return itemOrderEntities.stream()
                    .map(this::toDomainObj)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }


    public ItemOrderEntity toEntity(ItemOrder itemOrder) {
        ItemOrderEntity itemOrderEntity = new ItemOrderEntity();
        itemOrderEntity.setId(itemOrder.id());
        itemOrderEntity.setQuantity(itemOrder.quantity());
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(itemOrder.productId());
        itemOrderEntity.setProduto(productEntity);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(itemOrder.orderId());
        itemOrderEntity.setOrder(orderEntity);
        return itemOrderEntity;
    }

}
