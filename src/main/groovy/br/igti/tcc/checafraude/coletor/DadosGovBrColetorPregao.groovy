package br.igti.tcc.checafraude.coletor

import br.igti.tcc.checafraude.service.PregaoService
// import br.igti.tcc.checafraude.entidades.PregaoEntidade
import br.igti.tcc.checafraude.util.JsonNodeUtil
import com.fasterxml.jackson.databind.JsonNode
// import com.fasterxml.jackson.databind.DeserializationFeature
import org.slf4j.Logger
// import com.fasterxml.jackson.databind.ObjectMapper
// import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
// import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

//Classe responsavel pela coleta de pregoes na API Dados.gov.br
@Component
class DadosGovBrColetorPregao extends ColetorBase implements ColetorInterface {

    private static final Logger log = LoggerFactory.getLogger(DadosGovBrColetorPregao.class)

    @Autowired
    PregaoService service

    @Autowired
    JsonNodeUtil jsonNodeUtil

    @Scheduled(fixedRate = 3000L)
     void coletar() {
        // TODO implementar a coleta na API
        log.info(" Coletor de pregoes")
    }
}