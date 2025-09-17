create database logistica;

use logistica;


create table cliente(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf_cnpj VARCHAR(20) NOT NULL,
endereco VARCHAR(100),
cidade VARCHAR(200),
estado VARCHAR(200)
);
SELECT id FROM cliente WHERE id = 1;

select * from cliente;

create table motorista(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cnh VARCHAR(20) NOT NULL,
veiculo VARCHAR(100),
cidade_base VARCHAR(200)
);

select * from motorista;

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    data_pedido DATE NOT NULL,
    volume_m3 DECIMAL(10,2),
    peso_kg DECIMAL(10,2),
    status ENUM('PENDENTE', 'ENTREGUE', 'CANCELADO') DEFAULT 'PENDENTE',
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

 select * from pedido;
ALTER TABLE pedido
MODIFY COLUMN data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE pedido DROP FOREIGN KEY pedido_ibfk_1;

/*Corrige o C maiusculo que deveria ser minusculo de references cliente(id)*/
ALTER TABLE pedido ADD FOREIGN KEY (cliente_id) REFERENCES cliente(id);


CREATE TABLE entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    motorista_id INT NOT NULL,
    data_saida DATE,
    data_entrega DATE,
    status ENUM('EM_ROTA', 'ENTREGUE', 'ATRASADA') DEFAULT 'EM_ROTA',
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (motorista_id) REFERENCES motorista(id)
);

select*from entrega;

SELECT id,pedido_id FROM entrega;



CREATE TABLE historico_entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrega_id INT NOT NULL,
    data_evento DATETIME NOT NULL,
    descricao VARCHAR(255),
    FOREIGN KEY (entrega_id) REFERENCES entrega(id)
);

select * from historico_entrega;


