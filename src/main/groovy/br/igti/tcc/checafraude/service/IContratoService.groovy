package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.ContratoEntidade

interface IContratoService {

    List<ContratoEntidade> findAll()
    ContratoEntidade saveAndFlush(ContratoEntidade entidade)
    void  saveAll(List<ContratoEntidade> lista)
    void deleteById(Long id)
}