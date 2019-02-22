CREATE TABLE material (
  identificador BIGINT(20) NOT NULL,
  descricao VARCHAR(250),
  codigo VARCHAR(30),
  id_classe VARCHAR(30),
  id_grupo VARCHAR(30),
  id_pdm VARCHAR(30),
  status VARCHAR(30),
  sustentavel VARCHAR(30),
  PRIMARY KEY (identificador)
) ENGINE=MYISAM CHARSET=utf8 COLLATE=utf8_general_ci;