package br.com.pdv.order_api.infrastructure.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import br.com.pdv.order_api.infrastructure.controllers.request.PaymentRequest;
import br.com.pdv.order_api.infrastructure.controllers.response.PaymentResponse;

class PaymentServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private PaymentService paymentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testMakePayment() {
		PaymentRequest paymentRequest = new PaymentRequest();
		PaymentResponse paymentResponse = new PaymentResponse();

		when(restTemplate.postForObject(anyString(), eq(paymentRequest), eq(PaymentResponse.class)))
				.thenReturn(paymentResponse);

		PaymentResponse response = paymentService.makePayment(paymentRequest);

		assertNotNull(response);
		assertEquals(paymentResponse, response);
		verify(restTemplate, times(1)).postForObject(anyString(), eq(paymentRequest), eq(PaymentResponse.class));
	}

}
