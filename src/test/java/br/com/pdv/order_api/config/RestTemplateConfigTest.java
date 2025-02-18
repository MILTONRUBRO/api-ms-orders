package br.com.pdv.order_api.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RestTemplateConfigTest {

	@Test
	void testRestTemplateBean() {
		RestTemplateConfig config = new RestTemplateConfig();
		RestTemplate restTemplate = config.restTemplate();
		assertNotNull(restTemplate, "RestTemplate should not be null");
	}
}
