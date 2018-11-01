package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "contrato")
class ContratoEntidade {

    @Id
    Long identificador
    String objeto
    String uasg

    @Column(name = "valor_inicial")
    String valorInicial

    @Column(name = "numero_aditivo")
    String numeroAditivo

    @Column(name = "cnpj_contratada")
    String cnpjContratada

    @Column(name = "data_assinatura")
    String dataAssinatura

    @Column(name = "codigo_contrato")
    String codigoContrato

    @Column(name = "modalidade_licitacao")
    String modalidadeLicitacao

    @Column(name = "fundamento_legal")
    String fundamentoLegal

    @Column(name = "licitacao_associada")
    String licitacaoAssociada

    @Column(name = "data_inicio_vigencia")
    String dataInicioVigencia

    @Column(name = "data_termino_vigencia")
    String dataTerminoVigencia

    String numero

    ContratoEntidade() {
    }

    ContratoEntidade(Long identificador, String objeto) {
        this.identificador = identificador
        this.objeto = objeto
    }

    @Override
    String toString() {
        return "Contrato{" + "identificador=" + identificador + ", objeto=" + objeto + '}'
    }
}