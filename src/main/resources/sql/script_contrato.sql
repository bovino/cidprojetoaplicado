CREATE TABLE contrato (  
  identificador INT(11) NOT NULL,
  uasg VARCHAR(30),
  codigo_contrato VARCHAR(30),
  modalidade_licitacao VARCHAR(30),
  numero_aditivo VARCHAR(30),
  data_assinatura VARCHAR(30),
  valor_inicial VARCHAR(30),
  numero VARCHAR(30),
  objeto LONGTEXT,
  licitacao_associada VARCHAR(30),
  cnpj_contratada VARCHAR(30),
  data_inicio_vigencia VARCHAR(30),
  data_termino_vigencia VARCHAR(30),
  fundamento_legal VARCHAR(300),
  PRIMARY KEY (identificador)
) ENGINE=MYISAM CHARSET=utf8 COLLATE=utf8_general_ci;

ALTER TABLE `checafraude`.`contrato` ADD FULLTEXT INDEX `OBJTEXTO` (`objeto`); 