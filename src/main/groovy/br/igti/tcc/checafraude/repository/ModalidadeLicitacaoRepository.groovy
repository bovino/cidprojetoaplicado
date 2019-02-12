package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.ModalidadeLicitacaoEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface ModalidadeLicitacaoRepository extends JpaRepository<ModalidadeLicitacaoEntidade, Long> {
}