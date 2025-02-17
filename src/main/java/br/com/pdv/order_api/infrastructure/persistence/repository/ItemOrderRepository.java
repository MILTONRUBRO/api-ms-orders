package br.com.pdv.order_api.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pdv.order_api.infrastructure.persistence.entity.ItemOrderEntity;

public interface ItemOrderRepository extends JpaRepository<ItemOrderEntity, Long> {
	
	

}
