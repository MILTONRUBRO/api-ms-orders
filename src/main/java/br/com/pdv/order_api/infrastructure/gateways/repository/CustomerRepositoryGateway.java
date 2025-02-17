package br.com.pdv.order_api.infrastructure.gateways.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pdv.order_api.application.gateways.CustomerGateway;
import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;
import br.com.pdv.order_api.infrastructure.services.CustomerService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerRepositoryGateway implements CustomerGateway {
	
	@Autowired
	private CustomerService customerService;

	@Override
	public CustomerResponseDTO findByDocument(String document) {
		return  customerService.buscarClientePorCPF(document);
	}

	@Override
	public Optional<CustomerResponseDTO> findByDocumentForOrder(String document) {
		 CustomerResponseDTO client = customerService.buscarClientePorCPF(document);
		 
		 log.info("Response Customer Service {}", client);
		return Optional.of(client);
	}

}
