package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "modalidade_licitacao")
class ModalidadeLicitacaoEntidade {

    @Id
    Long identificador

    @Column(name = "descricao")
    String descricao

    @Column(name = "codigo")
    String codigo


    ModalidadeLicitacaoEntidade() {
    }

    ModalidadeLicitacaoEntidade(Long identificador, String descricao, String codigo) {
        this.identificador = identificador
        this.descricao = descricao
        this.codigo = codigo
    }

    @Override
    String toString() {
        return "Modalidade de Licitação{" + "identificador=" + identificador + ", descricao=" + descricao + '}'
    }
}