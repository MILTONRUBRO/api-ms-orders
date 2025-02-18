package br.com.pdv.order_api.infrastructure.controllers;

import br.com.pdv.order_api.application.usecase.OrderCreateInteractor;
import br.com.pdv.order_api.domain.entity.ItemOrder;
import br.com.pdv.order_api.domain.entity.Order;
import br.com.pdv.order_api.infrastructure.controllers.request.OrderRequest;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatus;
import br.com.pdv.order_api.infrastructure.persistence.entity.OrderStatusTest;
import io.cucumber.java.en.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderSteps {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderCreateInteractor createOrderUseCase;

    private String requestBody;
    private int statusCode;
    private String locationHeader;

    @Given("que eu tenho um pedido válido")
    public void queEuTenhoUmPedidoValido() {
        requestBody = "{\"item\":\"Produto A\", \"quantity\":2, \"price\":100.0}";
    }

    @When("eu envio uma requisição POST para {string} com o pedido")
    public void euEnvioUmaRequisicaoPOSTParaComOPedido(String path) throws Exception {
        MockitoAnnotations.openMocks(this);
        Order orderMock = new Order(1L, LocalDateTime.now(), OrderStatus.RECEIVED, 10D,"String", List.of(new ItemOrder(1L,1L,1L,1)));
        when(createOrderUseCase.createOrder(any(OrderRequest.class))).thenReturn(orderMock);
    }

    @Then("a resposta deve ter o código de status {string}")
    public void aRespostaDeveTerOCodigoDeStatus(String expectedStatus) {
        assert statusCode == Integer.parseInt(expectedStatus);
    }

    @Then("a resposta deve conter a URI do novo pedido")
    public void aRespostaDeveConterAURI() {
        assert locationHeader != null && locationHeader.contains("/orders/");
    }
}