package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.ModalidadeLicitacaoEntidade

interface IModalidadeLicitacaoService {

    List<ModalidadeLicitacaoEntidade> findAll()
    ModalidadeLicitacaoEntidade saveAndFlush(ModalidadeLicitacaoEntidade entidade)
    void  saveAll(List<ModalidadeLicitacaoEntidade> lista)
    void deleteById(Long id)
}