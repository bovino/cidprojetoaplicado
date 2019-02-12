package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.PregaoEntidade

interface IPregaoService {

    List<PregaoEntidade> findAll()
    PregaoEntidade saveAndFlush(PregaoEntidade entidade)
    void  saveAll(List<PregaoEntidade> lista)
    void deleteById(Long id)
}