package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.GrupoMaterialEntidade

interface IGrupoMaterialService {

    List<GrupoMaterialEntidade> findAll()
    GrupoMaterialEntidade saveAndFlush(GrupoMaterialEntidade entidade)
    void saveAll(List<GrupoMaterialEntidade> lista)
    void deleteById(Long id)
}