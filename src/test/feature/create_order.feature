Feature: Criar um pedido

  Scenario: Criar um pedido com sucesso
    Given que eu tenho um pedido válido
    When eu envio uma requisição POST para "/orders" com o pedido
    Then a resposta deve ter o código de status "201"
    And a resposta deve conter a URI do novo pedido
