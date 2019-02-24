package br.igti.tcc.checafraude.rest

import br.igti.tcc.checafraude.entidades.GrupoMaterialEntidade
import br.igti.tcc.checafraude.service.GrupoMaterialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrupoMaterialRest {

    @Autowired
    GrupoMaterialService service

    @RequestMapping("/grupo-material")
    public List<GrupoMaterialEntidade> findAll() {
        return service.findAll()
    }
}