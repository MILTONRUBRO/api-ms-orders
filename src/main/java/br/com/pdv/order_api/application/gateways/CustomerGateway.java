package br.com.pdv.order_api.application.gateways;

import java.util.Optional;

import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;

public interface CustomerGateway {
	
	CustomerResponseDTO findByDocument(String document);
	Optional<CustomerResponseDTO>findByDocumentForOrder(String document);


}
