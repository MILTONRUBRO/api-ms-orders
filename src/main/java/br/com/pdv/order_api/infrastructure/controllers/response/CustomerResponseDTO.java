package br.com.pdv.order_api.infrastructure.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
	private int id;
	private String name;
	private String email;
	private String document;
}
