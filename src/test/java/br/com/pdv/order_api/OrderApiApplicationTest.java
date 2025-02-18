package br.com.pdv.order_api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApiApplicationTest {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> OrderApiApplication.main(new String[] {}));
	}

}
