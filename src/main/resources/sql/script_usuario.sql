CREATE TABLE usuario
  (
     id            BIGINT(20) NOT NULL auto_increment,
     nome          TEXT,
     login         TEXT,
     senha         TEXT,
     email         TEXT,
     ativo         BOOLEAN,
     cpf           TEXT,
     orgaoentidade TEXT,
     PRIMARY KEY (id)
  )
engine=myisam;