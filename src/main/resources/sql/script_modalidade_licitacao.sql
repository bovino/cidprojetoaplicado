CREATE TABLE modalidade_licitacao (
  identificador BIGINT(20) NOT NULL,
  descricao VARCHAR(250),
  codigo VARCHAR(30),
  PRIMARY KEY (identificador)
) ENGINE=MYISAM CHARSET=utf8 COLLATE=utf8_general_ci;