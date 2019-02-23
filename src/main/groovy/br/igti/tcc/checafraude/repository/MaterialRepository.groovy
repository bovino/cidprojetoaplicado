package br.igti.tcc.checafraude.repository

import br.igti.tcc.checafraude.entidades.MaterialEntidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface MaterialRepository extends JpaRepository<MaterialEntidade, Long> {
}