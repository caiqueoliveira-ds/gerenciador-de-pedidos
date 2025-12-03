# gerenciador-de-pedidos

Sistema de Gestão de Pedidos - Lanchonete - SA
Visão Geral

Sistema desktop desenvolvido em Java Swing para gestão completa de pedidos de uma lanchonete, aplicando boas práticas de programação, padrões de projeto e arquitetura MVC.

    Funcionalidades
        -Funcionalidades Principais

        .Cadastro de Produtos – nome, descrição, preço
        .Cadastro de Clientes – informações completas dos clientes
        .Gestão de Pedidos – criação, edição e acompanhamento
        .Controle de Status – Em produção, pronto, entregue, concluído
        .Persistência de Dados – MySQL + CRUD


-Funcionalidades Avançadas

        .Relatórios de vendas em arquivo .TXT
        .Interface gráfica responsiva e intuitiva
        .Validação de dados e prevenção de erros


-Estrutura do Projeto
GERENCIADOR-DE-PEDIDOS/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/tecdess/gerenciador/
│       │       ├── controller/
│       │       │   ├── MainController.java
│       │       │   ├── PedidoController.java
│       │       │   └── ProdutoController.java
│       │       │
│       │       ├── model/
│       │       │   ├── entity/
│       │       │   │    ├── Cliente.java
│       │       │   │    ├── ItemPedido.java
│       │       │   │    ├── Pedido.java
│       │       │   │    └── Produto.java
│       │       │   │
│       │       │   └─── StatusPedido.java
│       │       │
│       │       ├── repository/
│       │       │   ├── ClienteRepository.java
│       │       │   ├── IClienteRepository.java
│       │       │   ├── IPedidoRepository.java
│       │       │   ├── IProdutoRepository.java
│       │       │   ├── PedidoRepository.java
│       │       │   └── ProdutoRepository.java
│       │       │
│       │       ├── service/
│       │       │   ├── ClienteService.java
│       │       │   ├── PedidoService.java
│       │       │   └── ProdutoService.java
│       │       │
│       │       ├── util/
│       │       │   └── Gerenciador.drawio
│       │       │
│       │       ├── view/
│       │       │   ├── ClienteForm.java
│       │       │   ├── MainView.java
│       │       │   ├── PedidoForm.java
│       │       │   └── ProdutoForm.java
│       │       │
│       │       └── Main.java
│       │
│       └── resources/
│           └── script.sql
│
├── target/
├── .gitattributes
├── LICENSE
├── pom.xml
├── README.md
└── relatorio.txt


-Padrões de Projeto

-Singleton – conexão com banco
-MVC – arquitetura principal
-Repository – acesso a dados
-Observer – atualização de status
-SOLID – princípios de OO


SCRIPT SQL

CREATE DATABASE gerenciador_pedidos;
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
    vl_produto DECIMAL(5,2) NOT NULL,
    dt_validade DATE NOT NULL,
    dt_fabricacao DATE NULL
);

CREATE TABLE T_GRP_ESTOQUE(

    id_estoque INT PRIMARY KEY AUTO_INCREMENT,
    tp_mov_estoque VARCHAR(20) NOT NULL,
    st_estoque VARCHAR(20) NOT NULL,
    qt_produto INT NOT NULL

);

CREATE TABLE T_PEDIDO_PRODUTO( /*ASSOCIATIVA*/

    id_pedido INT,
    id_produto INT,
    PRIMARY KEY (id_pedido, id_produto),
    FOREIGN KEY (id_pedido) REFERENCES T_GRP_PEDIDO(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES T_GRP_PRODUTO(id_produto)

);

CREATE TABLE T_PRODUTO_ESTOQUE( /*ASSOCIATIVA*/

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

CREATE TABLE T_CLIENTE_ENDERECO( /*ASSOCIATIVA*/

    id_cliente INT,
    id_endereco INT,
    PRIMARY KEY (id_cliente, id_endereco),
    FOREIGN KEY (id_cliente) REFERENCES T_GRP_CLIENTE(id_cliente),
    FOREIGN KEY (id_endereco) REFERENCES T_GRP_ENDERECO(id_endereco)

);

CREATE TABLE T_GRP_PAGAMENTO(

    id_pagamento INT NOT NULL,
    tp_pagamento CHAR(1) NOT NULL,
    dt_pagamento DATETIME NOT NULL,
    vl_pagamento DECIMAL(5,2) NOT NULL
);


Instalação e Execução
-Pré-requisitos

.Java JDK 11+
.MySQL 8.0+
.Maven 3.6+
.Git

Interface
Telas Incluídas

Dashboard
Gestão de Produtos
Gestão de Clientes
Novo Pedido
Acompanhamento de Pedidos

Tecnologias Utilizadas
-Backend

.Java 17
.Swing
.MySQL Connector
.JUnit 5

-Ferramentas
.Maven
.Git/GitHub
.MySQL Workbench
.StarUML


Documentação
UML

Diagrama de Classes
Diagrama de Casos de Uso
Diagrama de AtividadeS



Licença
Projeto desenvolvido para fins educacionais – SENAI Santa Catarina.

Desenvolvido por: Caíque Oliveira, Lucas Slomski, Daniel Souza e Maisa Marçal
Última atualização: 02/12/2025
Versão: 1.0.0

