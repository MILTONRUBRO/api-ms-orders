package br.com.pdv.order_api.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pdv.order_api.infrastructure.persistence.entity.OrderEntity;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
    @Modifying
    @Query("UPDATE OrderEntity o SET o.status = :status WHERE o.id = :id")
    void updateOrderStatus(Long id, OrderStatus status);

    List<OrderEntity> findByStatusNot(OrderStatus status);

    List<OrderEntity> findByIdAndStatusNot(Long id, OrderStatus status);


}
