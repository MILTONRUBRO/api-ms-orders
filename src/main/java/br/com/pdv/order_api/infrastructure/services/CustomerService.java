package br.com.pdv.order_api.infrastructure.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api-ms-client-f3a4d4cad45a.herokuapp.com/customers/";

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerResponseDTO buscarClientePorCPF(String cpf) {
        String url = BASE_URL + cpf;
        ResponseEntity<CustomerResponseDTO> response = restTemplate.getForEntity(url, CustomerResponseDTO.class);
        
        
        return response.getBody();
    }
}