package br.igti.tcc.checafraude.service

import br.igti.tcc.checafraude.entidades.GrupoMaterialEntidade
import br.igti.tcc.checafraude.repository.GrupoMaterialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GrupoMaterialService implements IGrupoMaterialService {

    @Autowired
    private GrupoMaterialRepository repository

    @Override
    List<GrupoMaterialEntidade> findAll() {
        List<GrupoMaterialEntidade> contratos = (List<GrupoMaterialEntidade>) repository.findAll()
        return contratos
    }

    @Override
    GrupoMaterialEntidade saveAndFlush(GrupoMaterialEntidade entidade) {
        return repository.saveAndFlush(entidade)
    }

    @Override
    void deleteById(Long id) {
        GrupoMaterialEntidade entidade = repository.findById(id)
        repository.delete(entidade)
        repository.flush()
    }

    @Override
    void saveAll(List<GrupoMaterialEntidade> lista) {
        repository.saveAll(lista)
        repository.flush()
    }
}