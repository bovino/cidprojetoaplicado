package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.CompraSemLicitacaoEntidade
import br.igti.tcc.checafraude.repository.CompraSemLicitacaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompraSemLicitacaoService implements ICompraSemLicitacaoService {

    @Autowired
    private CompraSemLicitacaoRepository repository

    @Override
    List<CompraSemLicitacaoEntidade> findAll() {
        List<CompraSemLicitacaoEntidade> contratos = (List<CompraSemLicitacaoEntidade>) repository.findAll()
        return contratos
    }

    @Override
    CompraSemLicitacaoEntidade saveAndFlush(CompraSemLicitacaoEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        CompraSemLicitacaoEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<CompraSemLicitacaoEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}