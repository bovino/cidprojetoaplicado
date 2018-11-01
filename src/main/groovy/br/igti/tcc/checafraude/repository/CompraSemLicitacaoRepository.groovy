package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.CompraSemLicitacaoEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface CompraSemLicitacaoRepository extends JpaRepository<CompraSemLicitacaoEntidade, Long> {
}