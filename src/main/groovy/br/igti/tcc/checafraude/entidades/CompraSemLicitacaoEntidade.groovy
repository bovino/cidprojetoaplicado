package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "compra_sem_licitacao")
class CompraSemLicitacaoEntidade {

  @Id
  @GeneratedValue
  Long id

  @Column(name = "co_orgao")
  Long codOrgao

  @Column(name = "co_uasg")
  String codUasg

  @Column(name = "co_modalidade_licitacao")
  String codModalidadeLicitacao

  @Column(name = "nu_processo")
  String numeroProcesso

  @Column(name = "vr_estimado")
  String valorEstimado

  @Column(name = "ds_objeto_licitacao")
  String objetoLicitacao

  @Column(name = "ds_fundamento_legal")
  String fundamentoLegal

  @Column(name = "ds_justificativa")
  String justificativa

  String dtDeclaracaoDispensa

  @Column(name = "no_responsavel_decl_disp")
  String nomeResponsavelDeclaracaoDispensa // ÁLVARO BITTENCOURT HENRIQUE SILVA

  @Column(name = "no_cargo_resp_decl_disp")
  String cargoResponsavelDeclaracaoDispensa // Coordenador-Geral de Serviços Gerais""

  String dtRatificacao

  @Column(name = "no_responsavel_ratificacao")
  String nomeResponsavelRatificacao // JANUÁRIO MONTONE""

  @Column(name = "no_cargo_resp_ratificacao")
  String cargoResponsavelRatificacao // Subsecretário de Assuntos Administrativos""

  String dtPublicacao
}