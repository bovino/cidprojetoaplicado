package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.FornecedorEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface FornecedorRepository extends JpaRepository<FornecedorEntidade, Long> {
}