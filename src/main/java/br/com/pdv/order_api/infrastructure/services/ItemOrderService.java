package br.com.pdv.order_api.infrastructure.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.ProductEntity;
import br.com.pdv.order_api.infrastructure.persistence.repository.ProductRepository;

@Service
public class ItemOrderService {
	
	@Autowired
	private   ProductRepository productRepository;
	
    public List<ItemOrderEntity> criarItensOrderMockados(OrderEntity orderEntity) {
        List<ItemOrderEntity> itensOrder = new ArrayList<>();
        
       ItemOrderEntity item = new ItemOrderEntity();
                               
       item.setQuantity(1);
       item.setTotalValue(BigDecimal.valueOf(45,99));
       item.setOrder(orderEntity);
        
       itensOrder.add(item);
        
       return itensOrder;
    }
	


}
