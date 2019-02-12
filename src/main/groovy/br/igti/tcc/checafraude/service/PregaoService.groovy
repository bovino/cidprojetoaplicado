package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.PregaoEntidade
import br.igti.tcc.checafraude.repository.PregaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PregaoService implements IPregaoService {

    @Autowired
    private PregaoRepository repository

    @Override
    List<PregaoEntidade> findAll() {
        List<PregaoEntidade> contratos = (List<PregaoEntidade>) repository.findAll()
        return contratos
    }

    @Override
    PregaoEntidade saveAndFlush(PregaoEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        PregaoEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<PregaoEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}