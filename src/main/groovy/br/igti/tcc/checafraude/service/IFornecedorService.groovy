package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.FornecedorEntidade

interface IFornecedorService {

    List<FornecedorEntidade> findAll()
    FornecedorEntidade saveAndFlush(FornecedorEntidade entidade)
    void  saveAll(List<FornecedorEntidade> lista)
    void deleteById(Long id)
}