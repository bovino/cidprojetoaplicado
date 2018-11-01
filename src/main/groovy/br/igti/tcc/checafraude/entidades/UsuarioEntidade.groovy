package br.igti.tcc.checafraude.entidades

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "usuario")
class UsuarioEntidade {

    @Id
    @GeneratedValue
    Long id
    String nome
    String login
    String senha
    int ativo
    String orgaoentidade

    UsuarioEntidade() {
    }

    UsuarioEntidade(Long identificador, String nome) {
        this.id = identificador
        this.nome = nome
    }

    @Override
    String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + '}'
    }
}