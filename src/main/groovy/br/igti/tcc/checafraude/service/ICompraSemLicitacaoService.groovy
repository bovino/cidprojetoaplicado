package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.CompraSemLicitacaoEntidade

interface ICompraSemLicitacaoService {

    List<CompraSemLicitacaoEntidade> findAll()
    CompraSemLicitacaoEntidade saveAndFlush(CompraSemLicitacaoEntidade entidade)
    void  saveAll(List<CompraSemLicitacaoEntidade> lista)
    void deleteById(Long id)
}