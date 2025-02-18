package br.com.pdv.order_api.application.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {
	@Test
	void testNotFoundExceptionMessage() {
		String message = "Resource not found";
		NotFoundException exception = new NotFoundException(message);
		assertEquals(message, exception.getMessage());
	}

	@Test
	void testNotFoundExceptionIsRuntimeException() {
		NotFoundException exception = new NotFoundException("Resource not found");
		assertTrue(exception instanceof RuntimeException);
	}
}
