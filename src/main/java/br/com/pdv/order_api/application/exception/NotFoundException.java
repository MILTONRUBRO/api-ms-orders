package br.com.pdv.order_api.application.exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

}
