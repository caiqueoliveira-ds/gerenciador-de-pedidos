CREATE DATABASE IF NOT EXISTS gerenciador_pedidos;
USE gerenciador_pedidos;

CREATE TABLE T_GRP_CLIENTE(
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nm_cliente VARCHAR(150) NOT NULL,
    nr_cpf CHAR(14) NOT NULL UNIQUE,
    ds_email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE T_GRP_PEDIDO(
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    cd_pedido INT NOT NULL UNIQUE,
    st_pedido CHAR(1) NOT NULL,
    ds_pedido VARCHAR(80) NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES T_GRP_CLIENTE(id_cliente)
);

CREATE TABLE T_GRP_PRODUTO(
    id_produto INT PRIMARY KEY AUTO_INCREMENT,
    cd_produto INT NOT NULL UNIQUE,
    nm_produto VARCHAR(80) NOT NULL,
    st_produto CHAR(1) NOT NULL,
    vl_produto DECIMAL(10,2) NOT NULL,

);

CREATE TABLE T_GRP_ESTOQUE(
    id_estoque INT PRIMARY KEY AUTO_INCREMENT,
    tp_mov_estoque VARCHAR(20) NOT NULL,
    st_estoque VARCHAR(20) NOT NULL,
    qt_produto INT NOT NULL
);

CREATE TABLE T_PEDIDO_PRODUTO(
    id_pedido INT,
    id_produto INT,
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES T_GRP_PEDIDO(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES T_GRP_PRODUTO(id_produto)
);

CREATE TABLE T_PRODUTO_ESTOQUE(
    id_produto INT,
    id_estoque INT,
    PRIMARY KEY (id_produto, id_estoque),
    FOREIGN KEY (id_produto) REFERENCES T_GRP_PRODUTO(id_produto),
    FOREIGN KEY (id_estoque) REFERENCES T_GRP_ESTOQUE(id_estoque)
);

CREATE TABLE T_GRP_ITEM_PEDIDO(
    id_item_pedido INT PRIMARY KEY AUTO_INCREMENT,
    qtd_item INT NOT NULL,
    ds_observacao VARCHAR(100) NOT NULL,
    id_pedido INT,
    id_produto INT,
    FOREIGN KEY (id_pedido) REFERENCES T_GRP_PEDIDO(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES T_GRP_PRODUTO(id_produto)
);

CREATE TABLE T_GRP_ENDERECO(
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    sg_estado CHAR(2) NOT NULL,
    cd_cidade_ibge CHAR(7) NOT NULL,
    nm_cidade VARCHAR(100) NOT NULL,
    nm_bairro VARCHAR(100) NOT NULL,
    tp_logradouro CHAR(1) NOT NULL,
    nm_logradouro VARCHAR(100) NOT NULL,
    nr_residencia INT NOT NULL,
    ob_complementar VARCHAR(150)
);

CREATE TABLE T_CLIENTE_ENDERECO(
    id_cliente INT,
    id_endereco INT,
    PRIMARY KEY (id_cliente, id_endereco),
    FOREIGN KEY (id_cliente) REFERENCES T_GRP_CLIENTE(id_cliente),
    FOREIGN KEY (id_endereco) REFERENCES T_GRP_ENDERECO(id_endereco)
);

CREATE TABLE T_GRP_PAGAMENTO(
    id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    tp_pagamento CHAR(1) NOT NULL,
    dt_pagamento DATETIME NOT NULL,
    vl_pagamento DECIMAL(10,2) NOT NULL
);