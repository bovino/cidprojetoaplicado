package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "grupo_material")
class GrupoMaterialEntidade {

    @Id
    Long identificador

    @Column(name = "descricao")
    String descricao

    @Column(name = "codigo")
    String codigo


    GrupoMaterialEntidade() {
    }

    GrupoMaterialEntidade(Long identificador, String descricao, String codigo) {
        this.identificador = identificador
        this.descricao = descricao
        this.codigo = codigo
    }

    @Override
    String toString() {
        return "Grupo de Material{" + "identificador=" + identificador + ", descricao=" + descricao + '}'
    }
}