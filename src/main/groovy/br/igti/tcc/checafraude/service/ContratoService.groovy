package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.ContratoEntidade
import br.igti.tcc.checafraude.repository.ContratoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContratoService implements IContratoService {

    @Autowired
    private ContratoRepository repository

    @Override
    List<ContratoEntidade> findAll() {
        List<ContratoEntidade> contratos = (List<ContratoEntidade>) repository.findAll()
        return contratos
    }

    @Override
    ContratoEntidade saveAndFlush(ContratoEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        ContratoEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<ContratoEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}