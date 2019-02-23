package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.MaterialEntidade

interface IMaterialService {

    List<MaterialEntidade> findAll()
    MaterialEntidade saveAndFlush(MaterialEntidade entidade)
    void saveAll(List<MaterialEntidade> lista)
    void deleteById(Long id)
}