package br.com.pdv.order_api.infrastructure.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;

class CustomerServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CustomerService customerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBuscarClientePorCPF() {
		String cpf = "12345678900";
		CustomerResponseDTO mockResponse = new CustomerResponseDTO();
		ResponseEntity<CustomerResponseDTO> responseEntity = ResponseEntity.ok(mockResponse);

		when(restTemplate.getForEntity(anyString(), eq(CustomerResponseDTO.class))).thenReturn(responseEntity);

		CustomerResponseDTO result = customerService.buscarClientePorCPF(cpf);

		assertNotNull(result);
		assertEquals(mockResponse, result);
		verify(restTemplate, times(1)).getForEntity(anyString(), eq(CustomerResponseDTO.class));
	}

	@Test
	void testBuscarClientePorCPF_NotFound() {
		String cpf = "12345678900";

		when(restTemplate.getForEntity(anyString(), eq(CustomerResponseDTO.class)))
				.thenReturn(ResponseEntity.notFound().build());

		CustomerResponseDTO result = customerService.buscarClientePorCPF(cpf);

		assertNull(result);
		verify(restTemplate, times(1)).getForEntity(anyString(), eq(CustomerResponseDTO.class));
	}
}
