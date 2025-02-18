package br.com.pdv.order_api.infrastructure.gateways.repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.pdv.order_api.infrastructure.publisher.OrderPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.application.exception.NotFoundException;
import br.com.pdv.order_api.application.gateways.CustomerGateway;
import br.com.pdv.order_api.application.gateways.OrderGateway;
import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.controllers.request.PaymentRequest;
import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;
import br.com.pdv.order_api.infrastructure.controllers.response.OrdersResponse;
import br.com.pdv.order_api.infrastructure.controllers.response.PaymentResponse;
import br.com.pdv.order_api.infrastructure.gateways.mapper.OrderEntityMapper;
import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import br.com.pdv.order_api.infrastructure.persistence.entity.PaymentMethod;
import br.com.pdv.order_api.infrastructure.persistence.entity.PaymentStatus;
import br.com.pdv.order_api.infrastructure.persistence.repository.ItemOrderRepository;
import br.com.pdv.order_api.infrastructure.persistence.repository.OrderRepository;
import br.com.pdv.order_api.infrastructure.services.ItemOrderService;
import br.com.pdv.order_api.infrastructure.services.PaymentService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderRepositoryGateway implements OrderGateway {
	
    final String IDENTIFICATION_PREFIX = "SEM-IDENTIFICACAO-";
    
    @Autowired
    private  OrderRepository orderRepository;
    
    @Autowired
    private  OrderEntityMapper orderEntityMapper;
    
    @Autowired
    private  CustomerGateway customerGateway;
    
    @Autowired
    private ItemOrderService itemOrderService;
    
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private OrderPublisher orderPublisher;


    
    @Autowired
    private PaymentService paymnetService;
    

    @Override
    @Transactional
    public Order createOrder(OrderRequest request) {
        log.info("Initializing order creation");
        CustomerResponseDTO customer = findOrCreateCustomer(request);
        OrderEntity orderEntity = mapAndPrepareOrderEntity(customer);
       
        Long orderId = orderEntity.getId();
        
        addItens(orderEntity);
        log.info("Order with ID {} created successfully!", orderId);
        
        addPayment(orderEntity);
        return orderEntityMapper.toDomainObj(orderEntity);
    }

    private void addPayment(OrderEntity orderEntity) {
    	
    	PaymentRequest paymentRequest = PaymentRequest.builder()
		    	.amount(orderEntity.getTotalValue())
		    	.status(PaymentStatus.REFUSED.getStatus())
		    	.order_id(String.valueOf(orderEntity.getId()))
		    	.payment_type(PaymentMethod.PIX.getPaymentType())
		    	.build();
		
    	PaymentResponse paymentReponse = paymnetService.makePayment(paymentRequest);
    	
    	orderEntity.setIdPayment(paymentReponse.getUuid());
    	
    	orderRepository.save(orderEntity);
	}

	@Override
    public void updateOrderStatus(Long idOrder, String status) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(idOrder);
        if (!optionalOrder.isPresent()) {
            throw new NotFoundException("Pedido não encontrado");
        }
        optionalOrder.get().setStatus(OrderStatus.fromString(status));
        orderRepository.save(optionalOrder.get());
    }

    @Override
    public void updateOrderPayments(Long idOrder, String status) {

        Optional<OrderEntity> optionalOrder = orderRepository.findById(idOrder);
        if (!optionalOrder.isPresent()) {
            throw new NotFoundException("Pedido não encontrado");
        }
        optionalOrder.get().setStatus(OrderStatus.fromString(status));
        if(status.equals("Recebido")){
            optionalOrder.get().setPaymentStatus(PaymentStatus.APPROVED);
            optionalOrder.get().setStatus(OrderStatus.fromString("Em Preparação"));

        }
        orderRepository.save(optionalOrder.get());
    }

	@Override
	public String getOrderPaymentSatus(Long idOrder) {
		Optional<OrderEntity> optionalOrder = orderRepository.findById(idOrder);
        
		if (!optionalOrder.isPresent()) {
            throw new NotFoundException("Pedido não encontrado");
        }
		
		return optionalOrder.get().getPaymentStatus().getStatus();
	}

    @Override
    public List<OrdersResponse> getAllOrdersOrdenedInteractor() {
        List<OrderEntity> orders = orderRepository.findByStatusNot(OrderStatus.FINALIZED);
        return orders.stream()
                .sorted(Comparator.comparing(this::getPriority)
                        .thenComparing(OrderEntity::getData))
                .map(orderEntityMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void publishProductionOrder(List<OrdersResponse> ordersResponses ) {
        this.orderPublisher.publishOrder(ordersResponses);
    }


    private CustomerResponseDTO findOrCreateCustomer(OrderRequest request) {
        return customerGateway.findByDocumentForOrder(request.documentNumber())
               .orElseGet(() -> new CustomerResponseDTO());
   }

    private OrderEntity mapAndPrepareOrderEntity(CustomerResponseDTO customer) {
        OrderEntity orderEntity = new OrderEntity();
        
        orderEntity.setClientDocument(customer.getDocument());
        orderEntity.setData(LocalDateTime.now());
        orderEntity.setStatus(OrderStatus.fromString("Recebido"));
        
        return orderRepository.save(orderEntity);
    }
    
    private void addItens(OrderEntity orderEntity) {
    	List<ItemOrderEntity> listaItens = itemOrderService.criarItensOrderMockados(orderEntity);
        orderEntity.setItens(listaItens);
    	itemOrderRepository.saveAll(listaItens);
    
    }


    private int getPriority(OrderEntity order) {
        return switch (order.getStatus()) {
            case COMPLETED -> 1;
            case PROCESSING -> 2; 
            case RECEIVED -> 3;
            default -> 4;
        };
    }

}
