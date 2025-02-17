MERGE INTO category (id, name) 
KEY(id) 
VALUES 
(1, 'Hambúrgueres'),
(2, 'Bebidas'),
(3, 'Acompanhamentos');

MERGE INTO product (id, name, description, price, category_id) 
KEY(id) 
VALUES
(1, 'Cheeseburger', 'Delicioso cheeseburger com alface, tomate e queijo', 5.99, 1),
(2, 'Sanduíche de Frango', 'Sanduíche de frango crocante com picles e maionese', 6.49, 1),
(3, 'Hambúrguer Vegano', 'Saboroso hambúrguer vegano com abacate e espinafre', 6.99, 1),
(4, 'Cola', 'Bebida de cola refrescante', 1.99, 2),
(5, 'Limonada', 'Limonada recém espremida', 2.49, 2),
(6, 'Milkshake', 'Milkshake cremoso de baunilha', 3.49, 2),
(7, 'Batata Frita', 'Batata frita dourada e crocante', 2.99, 3),
(8, 'Anéis de Cebola', 'Anéis de cebola crocantes', 3.49, 3),
(9, 'Salada', 'Salada fresca do jardim com molho', 4.99, 3);