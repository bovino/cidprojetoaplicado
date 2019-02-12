package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.ModalidadeLicitacaoEntidade
import br.igti.tcc.checafraude.repository.ModalidadeLicitacaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModalidadeLicitacaoService implements IModalidadeLicitacaoService {

    @Autowired
    private ModalidadeLicitacaoRepository repository

    @Override
    List<ModalidadeLicitacaoEntidade> findAll() {
        List<ModalidadeLicitacaoEntidade> modalidades = (List<ModalidadeLicitacaoEntidade>) repository.findAll()
        return modalidades
    }

    @Override
    ModalidadeLicitacaoEntidade saveAndFlush(ModalidadeLicitacaoEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        ModalidadeLicitacaoEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<ModalidadeLicitacaoEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}