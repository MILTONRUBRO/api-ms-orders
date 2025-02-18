package br.com.pdv.order_api.infrastructure.gateways.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.pdv.order_api.infrastructure.controllers.response.CustomerResponseDTO;
import br.com.pdv.order_api.infrastructure.services.CustomerService;

class CustomerRepositoryGatewayTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerRepositoryGateway customerRepositoryGateway;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByDocument() {
		String document = "123456789";
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		when(customerService.buscarClientePorCPF(document)).thenReturn(customerResponseDTO);

		CustomerResponseDTO result = customerRepositoryGateway.findByDocument(document);

		assertNotNull(result);
		assertEquals(customerResponseDTO, result);
		verify(customerService, times(1)).buscarClientePorCPF(document);
	}

	@Test
	void testFindByDocumentForOrder() {
		String document = "123456789";
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		when(customerService.buscarClientePorCPF(document)).thenReturn(customerResponseDTO);

		Optional<CustomerResponseDTO> result = customerRepositoryGateway.findByDocumentForOrder(document);

		assertTrue(result.isPresent());
		assertEquals(customerResponseDTO, result.get());
		verify(customerService, times(1)).buscarClientePorCPF(document);
	}
}
