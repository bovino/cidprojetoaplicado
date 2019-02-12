package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pregao")
class PregaoEntidade {

    @Id
    Long identificador

    @Column(name = "co_portaria")
    String codigoPortaria

    @Column(name = "co_processo")
    String codigoProcesso

    @Column(name = "co_uasg")
    String codigoUasg

    @Column(name = "ds_situacao_pregao")
    String situacaoPregao

    @Column(name = "ds_tipo_pregao")
    String tipoPregao

    @Column(name = "ds_tipo_pregao_compra")
    String tipoPregaoCompra

    @Column(name = "dt_data_edital")
    String dtDataEdital

    @Column(name = "dt_fim_proposta")
    String dtFimProposta

    @Column(name = "dt_inicio_proposta")
    String dtInicioProposta

    @Column(name = "dt_portaria")
    String dtPortaria

    @Column(name = "numero")
    String numero

    @Column(name = "tx_objeto")
    String textoObjeto

    PregaoEntidade() {
    }

    PregaoEntidade(Long identificador,
                   String codigoPortaria,
                   String codigoProcesso,
                   String codigoUasg,
                   String situacaoPregao,
                   String tipoPregao,
                   String tipoPregaoCompra,
                   String dtDataEdital,
                   String dtFimProposta,
                   String dtInicioProposta,
                   String dtPortaria,
                   String numero,
                   String textoObjeto) {
        this.identificador = identificador
        this.codigoPortaria = codigoPortaria
        this.codigoProcesso = codigoProcesso
        this.codigoUasg = codigoUasg
        this.situacaoPregao = situacaoPregao
        this.tipoPregao = tipoPregao
        this.tipoPregaoCompra = tipoPregaoCompra
        this.dtDataEdital = dtDataEdital
        this.dtFimProposta = dtFimProposta
        this.dtInicioProposta = dtInicioProposta
        this.dtPortaria = dtPortaria
        this.numero = numero
        this.textoObjeto = textoObjeto
    }

    @Override
    String toString() {
        return "Pregao {" + "identificador=" + identificador + ", numeroDoPregao=" + numero + '}'
    }
}