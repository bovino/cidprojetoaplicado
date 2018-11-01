package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.ContratoEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface ContratoRepository extends JpaRepository<ContratoEntidade, Long> {
}