package br.com.pdv.order_api.infrastructure.controllers.response;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerResponseDTOTest {

    @Test
    public void testCustomerResponseDTOConstructor() {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(1);
        customerResponseDTO.setName("John Doe");
        customerResponseDTO.setEmail("johndoe@example.com");
        customerResponseDTO.setDocument("123456789");

        assertEquals(1, customerResponseDTO.getId());
        assertEquals("John Doe", customerResponseDTO.getName());
        assertEquals("johndoe@example.com", customerResponseDTO.getEmail());
        assertEquals("123456789", customerResponseDTO.getDocument());
    }

    @Test
    public void testCustomerResponseDTOBuilder() {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(2);
        customerResponseDTO.setName("Jane Doe");
        customerResponseDTO.setEmail("janedoe@example.com");
        customerResponseDTO.setDocument("987654321");

        assertNotNull(customerResponseDTO);
        assertEquals(2, customerResponseDTO.getId());
        assertEquals("Jane Doe", customerResponseDTO.getName());
        assertEquals("janedoe@example.com", customerResponseDTO.getEmail());
        assertEquals("987654321", customerResponseDTO.getDocument());
    }

    @Test
    public void testToString() {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setId(3);
        customerResponseDTO.setName("Alice Example");
        customerResponseDTO.setEmail("alice@example.com");
        customerResponseDTO.setDocument("112233445");

        String expectedToString = "CustomerResponseDTO{id=3, name=Alice Example, email=alice@example.com, document=112233445}";
    }
}
