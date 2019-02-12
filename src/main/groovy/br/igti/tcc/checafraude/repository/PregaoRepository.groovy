package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.PregaoEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface PregaoRepository extends JpaRepository<PregaoEntidade, Long> {
}