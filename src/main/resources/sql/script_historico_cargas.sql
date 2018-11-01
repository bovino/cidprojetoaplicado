CREATE TABLE historico_cargas
  (
     contrato_max_offset              BIGINT(20),
     contrato_max_id                  BIGINT(20),
     compras_sem_licitacao_max_offset BIGINT(20),
     ompras_sem_licitacao_max_id      BIGINT(20),
     licitacao_max_offset             BIGINT(20),
     licitacao_max_id                 BIGINT(20),
     data_registro                    DATETIME,
     id                               BIGINT(20) NOT NULL auto_increment,
     PRIMARY KEY (id)
  )
engine=myisam;