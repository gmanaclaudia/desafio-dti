CREATE TABLE produtos (
     id serial PRIMARY KEY,
     nome varchar(250) NOT NULL,
     quantidade INT NOT NULL,
     valor_unitario decimal NOT NULL,
     disponivel boolean DEFAULT TRUE
);


CREATE TABLE historico_vendas (
	id serial PRIMARY KEY,
	id_produto INT REFERENCES produtos(id),
	quantidade INT NOT NULL,
	data_venda DATE NOT NULL,
	valor_unitario decimal NOT NULL
);

CREATE TABLE historico_cadastro (
	id serial PRIMARY KEY,
	id_produto INT REFERENCES produtos(id),
	quantidade INT NOT NULL,
	data_cadastro DATE NOT NULL,
	valor_unitario decimal NOT NULL
);


