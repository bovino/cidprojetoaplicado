CREATE TABLE pregao (
  identificador BIGINT(20) NOT NULL,
  co_portaria	VARCHAR(30),
  co_processo	VARCHAR(30),
  co_uasg	VARCHAR(30),
  ds_situacao_pregao	VARCHAR(30),
  ds_tipo_pregao	VARCHAR(30),
  ds_tipo_pregao_compra	VARCHAR(30),
  dt_data_edital	VARCHAR(30),
  dt_fim_proposta	VARCHAR(30),
  dt_inicio_proposta	VARCHAR(30),
  dt_portaria	VARCHAR(30),
  numero	VARCHAR(30),
  tx_objeto	TEXT,
  PRIMARY KEY (identificador)
) ENGINE=MYISAM CHARSET=utf8 COLLATE=utf8_general_ci;

ALTER TABLE `checafraude`.`pregao` ADD FULLTEXT INDEX `OBJTEXTO_PREGAO` (`tx_objeto`);