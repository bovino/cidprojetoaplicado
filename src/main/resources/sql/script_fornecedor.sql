CREATE TABLE fornecedor (
  identificador BIGINT(20) NOT NULL auto_increment,
  cpf	VARCHAR(30),
  cnpj	VARCHAR(30),
  uf	VARCHAR(30),
  id	VARCHAR(30),
  PRIMARY KEY (identificador)
) ENGINE=MYISAM CHARSET=utf8 COLLATE=utf8_general_ci;
