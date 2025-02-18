package br.com.pdv.order_api.infrastructure.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.pdv.order_api.infrastructure.controllers.request.PaymentRequest;
import br.com.pdv.order_api.infrastructure.controllers.response.PaymentResponse;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentService {
	
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://payments-tec-2bdc8333e6e8.herokuapp.com/payments";
	
    
    public PaymentService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

  public PaymentResponse makePayment(PaymentRequest paymentRequest) {
	  PaymentResponse response = restTemplate.postForObject(BASE_URL, paymentRequest, PaymentResponse.class);
	  log.info("Pagamento Response {}", response);
	  return response;
  }

}
