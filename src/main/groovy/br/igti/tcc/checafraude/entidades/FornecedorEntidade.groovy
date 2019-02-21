package br.igti.tcc.checafraude.entidades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fornecedor")
class FornecedorEntidade {

    @Id
    Long identificador

    @Column(name = "ativo")
    String ativo

    @Column(name = "cnpj")
    String cnpj

    @Column(name = "cpf")
    String cpf

    @Column(name = "habilitado_licitar")
    String habilitadoLicitar

    @Column(name = "id")
    String id

    @Column(name = "id_cnae")
    String idCnae

    @Column(name = "id_cnae2")
    String idCnae2

    @Column(name = "id_municipio")
    String idMunicipio

    @Column(name = "id_natureza_juridica")
    String idNaturezaJuridica

    @Column(name = "id_porte_empresa")
    String idPorteEmpresa

    @Column(name = "id_ramo_negocio")
    String idRamoNegocio

    @Column(name = "id_unidade_cadastradora")
    String idUnidadeCadastradora

    @Column(name = "nome")
    String nome

    @Column(name = "recadastrado")
    String isRecadastrado

    @Column(name = "uf")
    String uf

    FornecedorEntidade(){
    }

    FornecedorEntidade(Long identificador,
                       String ativo,
                       String cnpj,
                       String cpf,
                       String habilitadoLicitar,
                       String id,
                       String idCnae,
                       String idCnae2,
                       String idMunicipio,
                       String idNaturezaJuridica,
                       String idPorteEmpresa,
                       String idRamoNegocio,
                       String idUnidadeCadastradora,
                       String nome,
                       String isRecadastrado,
                       String uf) {
        this.identificador = identificador
        this.ativo = ativo
        this.cnpj = cnpj
        this.cpf = cpf
        this.habilitadoLicitar = habilitadoLicitar
        this.id = id
        this.idCnae = idCnae
        this.idCnae2 = idCnae2
        this.idMunicipio = idMunicipio
        this.idNaturezaJuridica = idNaturezaJuridica
        this.idPorteEmpresa = idPorteEmpresa
        this.idRamoNegocio = idRamoNegocio
        this.idUnidadeCadastradora = idUnidadeCadastradora
        this.nome = nome
        this.isRecadastrado = isRecadastrado
        this.uf = uf
    }

    @Override
    String toString() {
        return "Fornecedor {cnpj=" + cnpj + ", cpf= " + cpf + ",  identificador=" + identificador + ", id=" + id + " Estado=" + uf +  "}"
    }
}