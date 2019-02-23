package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.MaterialEntidade
import br.igti.tcc.checafraude.repository.MaterialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MaterialService implements IMaterialService {

    @Autowired
    private MaterialRepository repository

    @Override
    List<MaterialEntidade> findAll() {
        List<MaterialEntidade> contratos = (List<MaterialEntidade>) repository.findAll()
        return contratos
    }

    @Override
    MaterialEntidade saveAndFlush(MaterialEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        MaterialEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<MaterialEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}