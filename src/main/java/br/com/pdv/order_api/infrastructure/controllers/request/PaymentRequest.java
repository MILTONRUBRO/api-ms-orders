package br.com.pdv.order_api.infrastructure.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	private Double amount;
	private String order_id;
	private String method;
	private String status;
	private String payment_type;
}