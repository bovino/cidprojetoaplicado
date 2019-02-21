package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.FornecedorEntidade
import br.igti.tcc.checafraude.repository.FornecedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FornecedorService implements IFornecedorService {

    @Autowired
    private FornecedorRepository repository

    @Override
    List<FornecedorEntidade> findAll() {
        List<FornecedorEntidade> contratos = (List<FornecedorEntidade>) repository.findAll()
        return contratos
    }

    @Override
    FornecedorEntidade saveAndFlush(FornecedorEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        FornecedorEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<FornecedorEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}