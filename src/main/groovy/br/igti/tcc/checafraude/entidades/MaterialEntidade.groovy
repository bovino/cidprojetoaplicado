package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "material")
class MaterialEntidade {

    @Id
    Long identificador

    @Column
    String descricao

    @Column
    String codigo

    @Column
    String id_classe

    @Column
    String id_grupo

    @Column
    String id_pdm

    @Column
    String status

    @Column
    String sustentavel

    MaterialEntidade() {
    }

    MaterialEntidade(Long identificador,
                     String descricao,
                     String codigo,
                     String id_classe,
                     String id_grupo,
                     String id_pdm,
                     String status,
                     String sustentavel) {
        this.identificador = identificador
        this.descricao = descricao
        this.codigo = codigo
        this.id_classe = id_classe
        this.id_grupo = id_grupo
        this.id_pdm = id_pdm
        this.status = status
        this.sustentavel = sustentavel
    }

    @Override
    String toString() {
        return "Material{" + "identificador=" + identificador + ", descricao=" + descricao + '}'
    }
}