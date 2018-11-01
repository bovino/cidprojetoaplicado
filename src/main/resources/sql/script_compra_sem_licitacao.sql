CREATE TABLE compra_sem_licitacao (
  co_orgao bigint(20) DEFAULT NULL,
  co_uasg varchar(50) DEFAULT NULL,
  co_modalidade_licitacao varchar(50) DEFAULT NULL,
  nu_processo varchar(50) DEFAULT NULL,
  vr_estimado varchar(50) DEFAULT NULL,
  ds_objeto_licitacao text,
  ds_fundamento_legal text,
  ds_justificativa text,
  dtDeclaracaoDispensa varchar(50) DEFAULT NULL,
  no_responsavel_decl_disp varchar(100) DEFAULT NULL,
  no_cargo_resp_decl_disp varchar(100) DEFAULT NULL,
  dtRatificacao varchar(50) DEFAULT NULL,
  dtPublicacao varchar(50) DEFAULT NULL,
  no_responsavel_ratificacao varchar(100) DEFAULT NULL,
  no_cargo_resp_ratificacao varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
